package com.example.todolist.database

import androidx.room.*
import com.example.todolist.model.DetailItem
import com.example.todolist.model.ListParent
import kotlinx.coroutines.flow.Flow

@Dao
interface FilesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFile(file: ListParent);

    @Query("Select * from files_table ORDER BY id")
    fun getFiles(): List<ListParent>

    @Query("UPDATE files_table SET status = :status WHERE id = :fileId")
    fun updateFile(fileId: Int, status: String)

    @Query("Delete from files_table where id = :itemId")
    fun deleteFile(itemId: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTask(task: DetailItem)

    @Query("Select * from detail_item where parentId = :fileId ORDER BY id")
    fun getTasks(fileId: Int): List<DetailItem>

    @Query("Delete from detail_item where id = :taskId")
    fun deleteTask(taskId: Int)

    @Query("UPDATE detail_item SET status = :status WHERE id = :taskId")
    fun updateTask(taskId: Int, status: String)

    @Query("UPDATE detail_item SET status = :status WHERE parentId = :fileId")
    fun updateAllTasks(fileId: Int, status: String)
}