package com.example.practiceapplication3.data

import android.content.Context
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.practiceapplication3.workers.DatabaseWorker
import com.example.practiceapplication3.workers.DatabaseWorker.Companion.KEY_FILENAME


@Database(entities = [Note::class], version = 1, exportSchema = false)

abstract class NotesDatabase : RoomDatabase(){
    abstract fun notesDao(): NoteDao


    companion object{
        @Volatile private var instance: NotesDatabase? = null
        private val FILENAME = "sample"

        fun getInstance(context: Context): NotesDatabase? {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): NotesDatabase? {
            return Room.databaseBuilder(context, NotesDatabase::class.java, "notes")
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<DatabaseWorker>()
                                .setInputData(workDataOf(KEY_FILENAME to FILENAME))
                                .build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
    }



    }
}