package com.example.practiceapplication3.data.repository

import com.example.practiceapplication3.data.NoteDao
import com.example.practiceapplication3.data.model.Note
import javax.inject.Singleton

@Singleton
class NotesRepository(
    private val noteDao: NoteDao
) {

    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note) }


    fun updateNote(title: String, content:String, id:Int?){
        noteDao.updateNote(title, content, id) }


    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note) }


    //companion object {
    //    @Volatile
    //    private var instance: NoteRepository? = null
//
    //    fun getInstance(noteDao: NoteDao): NoteRepository{
    //        return instance ?: synchronized(this){
    //            instance ?: NoteRepository(noteDao).also { instance = it }
    //        }
    //    }
//
    //}
}