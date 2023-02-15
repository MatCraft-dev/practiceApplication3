package com.example.practiceapplication3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListNoteAdapter(
    private val layoutInflater: LayoutInflater,
    private val onClickListener: OnClickListener)
    : RecyclerView.Adapter<ListNoteViewHolder>(){

    private val listNote = mutableListOf<String>()

    fun setData(listNote: List<String>){
        this.listNote.clear()
        this.listNote.addAll(listNote)
        //notifyDataSetChanged()
    }

    private fun addNewItem(){
        listNote.add("") //TODO: Why the item is added 2 times ?? Find out
        notifyItemInserted(listNote.size) }

    fun updateItem(updatedText: String, itemPos: Int){
        listNote[itemPos] = updatedText
        notifyItemChanged(itemPos)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): ListNoteViewHolder {

        val view = layoutInflater.inflate(R.layout.item_list_view, parent, false)

        return ListNoteViewHolder(
            view,
            object: ListNoteViewHolder.OnKeyListener{
                override fun onEnterPressed() = addNewItem() },
            object: ListNoteViewHolder.OnClickListener{
                override fun onClick(pos: Int)
                    = onClickListener.onClick(pos)
                override fun onSelection(text: String, start: Int, end: Int)
                    = onClickListener.onSelection(text, start, end) })
    }


    override fun getItemCount() = listNote.size

    override fun onBindViewHolder(holder: ListNoteViewHolder, position: Int) {
        holder.bindData(listNote[position])
    }

    interface OnClickListener {
        fun onClick(pos: Int)
        fun onSelection(text: String, start: Int, end: Int)
    }


}