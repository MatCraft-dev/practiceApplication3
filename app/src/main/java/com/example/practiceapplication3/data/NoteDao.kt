package com.example.practiceapplication3.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM Notes")
    fun getNotes(): Flow<List<Note>>

    @Insert
    suspend fun insertNote(note: Note): Long

    @Insert
    suspend fun insertAll(noteList: List<Note>)

    @Delete
    suspend fun deleteNOte(note: Note)
}