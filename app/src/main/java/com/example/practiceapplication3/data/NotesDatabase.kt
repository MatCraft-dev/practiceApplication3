package com.example.practiceapplication3.data

import android.content.Context
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practiceapplication3.data.model.Note


@Database(entities = [Note::class], version = 1, exportSchema = false)

abstract class NotesDatabase : RoomDatabase(){
    abstract fun notesDao(): NoteDao

    companion object{
        @Volatile
        private var instance: NotesDatabase? = null
        //private const val FILENAME = "sample"

        fun getInstance(context: Context): NotesDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): NotesDatabase {
            return Room.databaseBuilder(
                context,
                NotesDatabase::class.java,
                "notes"
            )
                .allowMainThreadQueries()
                .build()
    }



    }
}