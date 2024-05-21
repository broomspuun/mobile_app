package com.example.android.lab.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val projectId: Int,
    val milestoneId: Int,
    val name: String,
    val description: String,
    val type: String,
    val status: String,
    val deadline: String
)


