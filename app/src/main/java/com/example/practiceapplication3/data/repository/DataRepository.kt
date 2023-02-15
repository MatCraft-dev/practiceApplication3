package com.example.practiceapplication3.data.repository

import com.example.practiceapplication3.data.NoteDao
import com.example.practiceapplication3.data.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    private val noteDao: NoteDao
) : DataRepositorySource {

    override fun getNote(id: Int): Note {
        return noteDao.getNote(id) }


    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes() }


    override suspend fun insertNote(note: Note){
        noteDao.insertNote(note) }


    override fun updateNote(title: String, content:String, id:Int?){
        noteDao.updateNote(title, content, id) }


    override suspend fun deleteNote(note: Note) {
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