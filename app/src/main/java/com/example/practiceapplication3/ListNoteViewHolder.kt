package com.example.practiceapplication3

import android.text.Html
import android.text.SpannableStringBuilder
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView

class ListNoteViewHolder(
    private val containerView: View,
    private val onKeyListener: OnKeyListener,
    private val onClickListener: OnClickListener)
    : RecyclerView.ViewHolder(containerView){

    val itemEditView: EditText
            get() =  containerView.findViewById(R.id.list_item)


    fun bindData(text: String) {
        //containerView.setOnClickListener{ onClickListener.onClick(text) }

        itemEditView.setText(text)

        itemEditView.setOnEditorActionListener { v, keyCode, event ->
            if (event.keyCode == KeyEvent.KEYCODE_ENTER){
                onKeyListener.onEnterPressed() }
            return@setOnEditorActionListener true }

        containerView.setOnClickListener{
            onClickListener.onClick(this.adapterPosition)
            onClickListener.onSelection(
                itemEditView.text.toString(),
                itemEditView.selectionStart,
                itemEditView.selectionEnd) }
    }


    interface OnClickListener {
        fun onClick(pos: Int)
        fun onSelection(text: String, start: Int, end: Int)

    }

    interface OnKeyListener{
        fun onEnterPressed()

    }


}