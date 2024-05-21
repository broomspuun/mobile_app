package com.example.android.lab.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.lab.data.AppDatabase
import com.example.android.lab.data.Task
import com.example.android.lab.data.TaskRepository

import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository

    init {
        val taskDao = AppDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
    }

    fun getTasksForProject(projectId: Int): LiveData<List<Task>> =
        repository.getTasksForProject(projectId)

    fun getTasksForMilestone(milestoneId: Int): LiveData<List<Task>> =
        repository.getTasksForMilestone(milestoneId)

    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    fun update(task: Task) = viewModelScope.launch {
        repository.update(task)
    }

    fun delete(task: Task) = viewModelScope.launch {
        repository.delete(task)
    }
}

