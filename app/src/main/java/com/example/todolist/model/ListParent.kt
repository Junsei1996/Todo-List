package com.example.todolist.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todolist.util.Enums


@Entity(tableName = "files_table")
data class ListParent(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name: String,
    var description: String,
    var status: String,
    var deadline:String
)
