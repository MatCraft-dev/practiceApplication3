package com.example.practiceapplication3.data.repository

import com.example.practiceapplication3.data.model.Note
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {

    fun getNotes(): Flow<List<Note>>
    fun getNote(id: Int): Note
    fun updateNote(title: String, content:String, id:Int?)
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)

}