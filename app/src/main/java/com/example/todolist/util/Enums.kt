package com.example.todolist.util

object Enums {

    enum class STATUS (key:String){
        ACTIVE("A")
    }


    enum class RESPONSE (key:Int){
        SUCCESS(200),
        FAILURE(400)
    }


}