package com.example.practiceapplication3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practiceapplication3.model.NoteUiModel
import com.google.android.material.elevation.SurfaceColors

const val TITLE = "TITLE"
const val CONTENT = "CONTENT"

class MainActivity : AppCompatActivity() {


    private val recyclerView by lazy {findViewById<RecyclerView>(R.id.recycle_view_for_notes)}
    private val notesAdapter by lazy {
        NotesAdapter(layoutInflater, object: NotesAdapter.OnClickListener{
            override fun onItemClick(notedata: NoteUiModel) = openNoteEditView(notedata) }
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val color = SurfaceColors.SURFACE_2.getColor(this)
        window.statusBarColor = color // Set color of system statusBar same as ActionBar
        window.navigationBarColor = color

        recyclerView?.adapter = notesAdapter
        recyclerView?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        notesAdapter.setData(
            listOf(
                NoteUiModel(
                    getString(R.string.head1),
                    getString(R.string.text1)
                ),
                NoteUiModel(
                    getString(R.string.head2),
                    getString(R.string.text2)
                ),
                NoteUiModel(
                    getString(R.string.head3),
                    getString(R.string.text3)
                )
            )
        )
    }

    fun openNoteEditView(notedata: NoteUiModel){
        val intent = Intent(this, NoteEditActivity::class.java)
        intent.putExtra(TITLE, notedata.title)
        intent.putExtra(CONTENT, notedata.content)
        startActivity(intent)
    }

}