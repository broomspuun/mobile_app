package com.example.android.lab.data

import android.content.Context
import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {
    fun getTasksForProject(projectId: Int): LiveData<List<Task>> =
        taskDao.getTasksForProject(projectId)

    fun getTasksForMilestone(milestoneId: Int): LiveData<List<Task>> =
        taskDao.getTasksForMilestone(milestoneId)

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }
}

