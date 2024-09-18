package com.example.todolist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todolist.model.Category
import com.example.todolist.model.DetailItem
import com.example.todolist.model.ListParent
import com.example.todolist.util.Enums
import kotlinx.coroutines.flow.Flow

@Dao
interface FilesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: Category);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFile(file: ListParent);
    @Query("Select * from categories_table")
    fun getCategories(): Flow<MutableList<Category>>
    @Query("Select * from files_table WHERE categoryId = :catId AND status = :stat ORDER BY id")
    fun getFilesByCategories(catId:Int, stat: String): Flow<MutableList<ListParent>>
    @Query("Select * from files_table WHERE status = :stat ORDER BY id")
    fun getFiles(stat: String): Flow<MutableList<ListParent>>
    @Query("Select * from files_table WHERE status = :stat ORDER BY id")
    fun getHistory(stat: String): Flow<MutableList<ListParent>>
    @Query("UPDATE files_table SET status = :status WHERE id = :fileId")
    fun updateFile(fileId: Int, status: String)
    @Query("Delete from files_table where id = :itemId")
    fun deleteFile(itemId: Int)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTask(task: DetailItem)
    @Query("Select * from detail_item where parentId = :fileId ORDER BY id")
    fun getTasks(fileId: Int): Flow<MutableList<DetailItem>>
    @Query("Delete from detail_item where id = :taskId")
    fun deleteTask(taskId: Int)
    @Query("UPDATE detail_item SET status = :status WHERE id = :taskId")
    fun updateTask(taskId: Int, status: String)
    @Query("UPDATE detail_item SET status = :status WHERE parentId = :fileId")
    fun updateAllTasks(fileId: Int, status: String)
}