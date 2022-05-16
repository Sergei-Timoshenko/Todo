package dev.sergeitimoshenko.todo.db

import androidx.room.*
import dev.sergeitimoshenko.todo.entities.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks WHERE title LIKE '%' || :searchQuery || '%'")
    fun getTasks(searchQuery: String): Flow<List<Task>>

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)
}