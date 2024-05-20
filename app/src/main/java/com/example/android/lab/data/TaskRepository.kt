package com.example.android.lab.data

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {

    fun getTasksForProject(projectId: Long): LiveData<List<Task>> {
        return taskDao.getTasksForProject(projectId)
    }

    fun getTasksForMilestone(milestoneId: Long): LiveData<List<Task>> {
        return taskDao.getTasksForMilestone(milestoneId)
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return taskDao.getAllTasks()
    }

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
