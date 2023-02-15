package com.example.practiceapplication3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practiceapplication3.data.model.Note

class NotesAdapter(
    private val layoutInflater: LayoutInflater,
    private val onClickListener: OnClickListener)
    : RecyclerView.Adapter<NoteViewHolder>(){

    private val notesData = mutableListOf<Note>()

    fun setData(notesData: List<Note>){
        this.notesData.clear()
        this.notesData.addAll(notesData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): NoteViewHolder {

        val view = layoutInflater.inflate(R.layout.item_note_view, parent, false)

        return NoteViewHolder(view, object: NoteViewHolder.OnClickListener{
            override fun onClick(notedata: Note)
            = onClickListener.onItemClick(notedata)

            override fun onLongPress(pos: Int, notedata: Note)
                = onClickListener.onItemLongPressed(pos, notedata)

        })

    }

    override fun getItemCount() = notesData.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindData(notesData[position])
    }

    interface OnClickListener {
        fun onItemClick(notedata: Note)
        fun onItemLongPressed(pos: Int, notedata: Note)
    }
}