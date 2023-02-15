package com.example.practiceapplication3.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note (
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content: String
){
    @PrimaryKey(true)
    var id: Int = 0
}

