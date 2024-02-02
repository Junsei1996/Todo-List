package com.example.todolist.database

import androidx.room.*
import com.example.todolist.model.ListParent
import kotlinx.coroutines.flow.Flow

@Dao
interface FilesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFile(file: ListParent);

    @Query("Select * from files_table ORDER BY id")
    suspend fun getFiles(): List<ListParent>

    @Query("Delete from files_table where id = :itemId")
    suspend fun deleteFile(itemId:Int)

}