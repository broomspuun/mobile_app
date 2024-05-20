package com.example.android.lab.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val projectId: Long,
    val milestoneId: Long?,
    val title: String,
    val description: String,
    val type: String,
    val status: String,
    val dueDate: String
)
