package com.example.practiceapplication3

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.practiceapplication3.data.model.Note
import com.example.practiceapplication3.data.NotesDatabase
import com.google.android.material.elevation.SurfaceColors
import kotlinx.coroutines.launch

class EmptyNoteActivity : AppCompatActivity(){

    private val titleEditTextView: EditText?
        get() = findViewById(R.id.text_title)
    private val contentEditTextView: EditText?
        get() = findViewById(R.id.text_content)

    private var title = ""
    private var content = ""

    private val noteDatabase by lazy {
        NotesDatabase.getInstance(this).notesDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty_note)
        window.statusBarColor = SurfaceColors.SURFACE_0.getColor(this) // Set color of system statusBar same as ActionBar
        window.navigationBarColor = SurfaceColors.SURFACE_2.getColor(this)
        titleEditTextView?.requestFocus()

    }


    override fun onPause() {
        super.onPause()
        title = titleEditTextView?.text.toString()
        content = contentEditTextView?.text.toString()

        lifecycleScope.launch{
            if(content.isNotEmpty()){
                noteDatabase.insertNote(Note(title, content))
            }
        }

    }



}