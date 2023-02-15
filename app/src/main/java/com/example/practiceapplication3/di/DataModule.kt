package com.example.practiceapplication3.di

import android.content.Context
import com.example.practiceapplication3.data.NoteDao
import com.example.practiceapplication3.data.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NotesDatabase {
        return NotesDatabase.getInstance(context)
    }

    @Provides
    fun provideNoteDao(notesDatabase: NotesDatabase): NoteDao {
        return notesDatabase.notesDao()
    }
}