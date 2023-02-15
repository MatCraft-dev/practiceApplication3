package com.example.practiceapplication3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.practiceapplication3.data.NoteDao
import com.example.practiceapplication3.data.model.Note
import com.example.practiceapplication3.data.NotesDatabase
import com.example.practiceapplication3.data.repository.DataRepository
import com.example.practiceapplication3.data.repository.DataRepositorySource
import com.example.practiceapplication3.ui.NotesViewModel
import com.google.android.material.elevation.SurfaceColors
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NoteEditActivity : AppCompatActivity() {

    private val titleEditTextView: EditText
        get() = findViewById(R.id.text_title)

    private val contentEditTextView: EditText
        get() = findViewById(R.id.text_content)

    //private val mViewModel: NotesViewModel by viewModels()

    @Inject lateinit var mViewModel: NotesViewModel

    //private val noteDatabase by lazy {
    //    NotesDatabase.getInstance(this).notesDao()
    //}




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_edit)

        window.statusBarColor = SurfaceColors.SURFACE_0.getColor(this) // Set color of system statusBar same as ActionBar
        window.navigationBarColor = SurfaceColors.SURFACE_2.getColor(this)

        mViewModel.initData(intent.getIntExtra(ID,0))
        mViewModel.title.observe(this, Observer<String>{title ->
            titleEditTextView.setText(title)
        })
        mViewModel.content.observe(this, Observer<String>{title ->
            contentEditTextView.setText(title)
        })


        //intent?.let{
        //    noteID = it.getIntExtra(ID, 0)
        //    val note: Note = noteDatabase.getNote(noteID)
        //
        //}

    }


    override fun onPause() {
        super.onPause()
        //title = titleEditTextView.text.toString()
        //content = contentEditTextView.text.toString()
//
        //lifecycleScope.launch{
        //    noteDatabase.updateNote(titleEditTextView.text.toString(),
        //        contentEditTextView.text.toString(),
        //        noteID)
        //}

    }

}