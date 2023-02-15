package com.example.practiceapplication3.data

import androidx.room.*
import com.example.practiceapplication3.data.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM Notes")
    fun getNotes(): Flow<List<Note>>

    @Query("UPDATE Notes SET title=:Title, content=:Content WHERE id=:ID")
    fun updateNote(Title: String, Content:String, ID:Int?)

    @Insert
    suspend fun insertNote(note: Note): Long

    @Insert()
    suspend fun insertAll(noteList: List<Note>)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM Notes WHERE id=:ID")
    fun getNote(ID: Int?): Note

}