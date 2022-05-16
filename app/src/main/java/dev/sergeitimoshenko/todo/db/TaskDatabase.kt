package dev.sergeitimoshenko.todo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.sergeitimoshenko.todo.entities.Task

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao
}