package com.example.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.model.DetailItem
import com.example.todolist.model.ListParent

@Database(entities = arrayOf(ListParent::class, DetailItem::class), version = 1, exportSchema = false)
public abstract class TodoDatabase : RoomDatabase() {

    abstract fun filesDao() : FilesDao

    companion object{

        private var INSTANCE : TodoDatabase? = null

        fun getDataBase(context: Context): TodoDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }



    }


}