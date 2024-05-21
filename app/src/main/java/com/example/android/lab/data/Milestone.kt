package com.example.android.lab.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "milestones")
data class Milestone(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val projectId: Int,
    val name: String,
    val deadline: String,
    val description: String
)
