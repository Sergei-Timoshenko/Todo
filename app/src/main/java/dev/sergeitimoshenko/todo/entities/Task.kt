package dev.sergeitimoshenko.todo.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tasks")
@Parcelize
data class Task(
    val title: String,
    val completed: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
) : Parcelable
