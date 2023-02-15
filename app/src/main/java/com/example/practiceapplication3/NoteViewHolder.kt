package com.example.practiceapplication3

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practiceapplication3.data.model.Note



class NoteViewHolder(
    private val containerView: View,
    private val onClickListener: OnClickListener)
    : RecyclerView.ViewHolder(containerView) {

    private val titleView: TextView
            by lazy { containerView.findViewById(R.id.textView) }
    private val contentView: TextView
            by lazy { containerView.findViewById(R.id.textView2) }

    fun bindData(notedata: Note) {
        containerView.setOnClickListener{onClickListener.onClick(notedata)}
        containerView.setOnLongClickListener{
            onClickListener.onLongPress(this.adapterPosition, notedata)
            return@setOnLongClickListener true}

        titleView.text = notedata.title
        contentView.text = notedata.content
    }

    interface OnClickListener {
        fun onClick(notedata: Note)
        fun onLongPress(pos: Int, notedata: Note)
    }
}