package com.example.practiceapplication3

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practiceapplication3.model.NoteUiModel



class NoteViewHolder(
    private val containerView: View,
    private val onClickListener: OnClickListener)
    : RecyclerView.ViewHolder(containerView) {

    private val titleView: TextView
            by lazy{containerView.findViewById(R.id.textView)}
    private val contentView: TextView
            by lazy { containerView.findViewById(R.id.textView2) }



    fun bindData(notedata: NoteUiModel) {
        containerView.setOnClickListener{onClickListener.onClick(notedata)}
        titleView.text = notedata.title
        contentView.text = notedata.content

    }

    interface OnClickListener {
        fun onClick(notedata: NoteUiModel)
    }
}