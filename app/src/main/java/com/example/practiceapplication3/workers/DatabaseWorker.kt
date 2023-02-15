package com.example.practiceapplication3.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.practiceapplication3.data.model.Note
import com.example.practiceapplication3.data.NotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader

class DatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(KEY_FILENAME)
            if (filename != null){
                applicationContext.assets.open(filename).use {inputStream ->
                    JsonReader(inputStream.reader()).use {jsonReader ->
                        val note = object : TypeToken<List<Note>>() {}.type
                        val notesList: List<Note> = Gson().fromJson(jsonReader, note)

                        val database = NotesDatabase.getInstance(applicationContext)
                        database.notesDao().insertAll(notesList)

                        Result.success()
                    }
                }

            }
            else{
                Log.e(TAG, "Error seeding database - no valid filename")
                Result.failure()
            }

        }
        catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object{
        private const val TAG = "Database Worker"
        const val KEY_FILENAME = "SAMPLE_DATA"
    }
}