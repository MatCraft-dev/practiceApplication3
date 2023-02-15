package com.example.practiceapplication3

import android.content.Context
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OpenNoteActivity : AppCompatActivity() {

    private val boldBtn : View by lazy {findViewById(R.id.bold)}
    //private val italicBtn: Button = findViewById(R.id.italic)
    //private val underlineBtn: Button = findViewById(R.id.italic)
    private var viewPos = 0
    private var vStart = 0
    private var vEnd = 0
    private var vText: String = ""

    private val titleEditTextView
        by lazy {findViewById<EditText>(R.id.text_title)}
    private val contentRecyclerView
        by lazy {findViewById<RecyclerView>(R.id.recyclerView_for_note_content)}
    private val listNoteAdapter
        by lazy {ListNoteAdapter(
            layoutInflater,
            object: ListNoteAdapter.OnClickListener{
                override fun onClick(pos: Int) {
                    viewPos = pos }
                override fun onSelection(text: String, start: Int, end: Int) {
                    vText = text//???????
                    vStart = start
                    vEnd = end } }
        )}



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_note)

        contentRecyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        contentRecyclerView.adapter = listNoteAdapter
        listNoteAdapter.setData( listOf("First") )

        //var holder = contentRecyclerView.findViewHolderForAdapterPosition(viewPos)



    }




}