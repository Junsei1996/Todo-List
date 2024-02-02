package com.example.todolist.model

import com.example.todolist.util.Enums

data class DetailItem(
    var id:Int,
    var name:String,
    var status: Enums.STATUS
)
