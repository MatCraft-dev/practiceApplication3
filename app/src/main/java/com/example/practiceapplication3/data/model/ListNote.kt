package com.example.practiceapplication3.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "List Notes")
data class ListNote(
    @ColumnInfo(name = "content") var content: ArrayList<String>
){
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0
}

