package com.example.todolist.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.todolist.util.Enums

@Entity(
    tableName = "detail_item",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = ListParent::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("parentId"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class DetailItem(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var parentId: Int,
    var name: String,
    var status: String
)
