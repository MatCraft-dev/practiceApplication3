package com.example.practiceapplication3.di

import android.content.Context
import com.example.practiceapplication3.data.NoteDao
import com.example.practiceapplication3.data.NotesDatabase
import com.example.practiceapplication3.data.repository.DataRepository
import com.example.practiceapplication3.data.repository.DataRepositorySource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DatabaseModule {

    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: DataRepository): DataRepositorySource

}