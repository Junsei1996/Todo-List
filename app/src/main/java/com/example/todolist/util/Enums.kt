package com.example.todolist.util

object Enums {

    enum class BUNDLE_KEYS(key: String){
        FILE_ID("FILE_ID"),
        TASK_ID("TASK_ID"),
        PARENT_FILE("PARENT_FILE")
    }

    enum class STATUS (key:String){
        ACTIVE("A"),
        COMPLETED("C")
    }


    enum class RESPONSE (key:Int){
        SUCCESS(200),
        FAILURE(400)
    }


}