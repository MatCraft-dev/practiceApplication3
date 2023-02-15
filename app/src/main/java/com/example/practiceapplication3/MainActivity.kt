package com.example.practiceapplication3

import android.content.Intent
import android.os.Bundle
import android.view.ActionMode
import android.view.ActionMode.Callback
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.practiceapplication3.data.model.Note
import com.example.practiceapplication3.data.NotesDatabase
import com.example.practiceapplication3.data.repository.DataRepository
import com.example.practiceapplication3.data.repository.DataRepositorySource
import com.example.practiceapplication3.ui.NoteEditActivity
import com.google.android.material.elevation.SurfaceColors
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

const val ID = "ID"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dataRepository: DataRepositorySource

    private val recyclerView by lazy {findViewById<RecyclerView>(R.id.recycle_view_for_notes)}

    private val notesAdapter by lazy {
        NotesAdapter(layoutInflater, object: NotesAdapter.OnClickListener{
            override fun onItemClick(notedata: Note) = openNoteEditView(notedata)

            override fun onItemLongPressed(pos: Int, notedata: Note) {
                val toolbar = findViewById<Toolbar>(R.id.toolbar)
                toolbar.startActionMode(object : Callback{
                    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                        menuInflater.inflate(R.menu.item_selected_top_bar, menu)
                        return true
                    }

                    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                        return false
                    }

                    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                        return when (item?.itemId){
                            R.id.delete -> {
                                deleteNote(notedata)
                                return true
                            } else -> false
                        }

                    }

                    override fun onDestroyActionMode(mode: ActionMode?) {

                    }
                })

            }
        }
        )
    }
    private val addButton: View by lazy {findViewById(R.id.add_button)}


    override fun onCreate(savedInstanbarceState: Bundle?) {
        super.onCreate(savedInstanbarceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = SurfaceColors.SURFACE_0.getColor(this)
        window.navigationBarColor = SurfaceColors.SURFACE_2.getColor(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView?.adapter = notesAdapter
        recyclerView?.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL) //LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        addButton.setOnClickListener{
            val intent = Intent(this, EmptyNoteActivity::class.java)
            startActivity(intent) }

        readNotes() }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_bar, menu)
        return true
    }


    fun openNoteEditView(notedata: Note){
        val intent = Intent(this, NoteEditActivity::class.java)
            .apply { putExtra(ID, notedata.id) }
        //intent.
        startActivity(intent) }


    private fun readNotes(){
        lifecycleScope.launch{
            dataRepository.getNotes().collect { notesList ->
                if (notesList.isNotEmpty())
                    notesAdapter.setData(notesList) } }
    }


    fun deleteNote(notedata: Note){
        lifecycleScope.launch{
            dataRepository.deleteNote(notedata) }
    }


}