package com.example.android.lab.viewmodel

import android.app.Application
import androidx.lifecycle.*

import com.example.android.lab.data.AppDatabase
import com.example.android.lab.data.Project
import com.example.android.lab.data.ProjectRepository
import kotlinx.coroutines.launch

class ProjectViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProjectRepository
    val allProjects: LiveData<List<Project>>

    // Используйте MutableLiveData для текущего проекта
    private val _currentProject = MutableLiveData<Project>()
    val currentProject: LiveData<Project> get() = _currentProject

    init {
        val projectDao = AppDatabase.getDatabase(application).projectDao()
        repository = ProjectRepository(projectDao)
        allProjects = repository.allProjects
    }

    fun insert(project: Project) = viewModelScope.launch {
        repository.insert(project)
    }

    fun update(project: Project) = viewModelScope.launch {
        repository.update(project)
    }

    fun delete(project: Project) = viewModelScope.launch {
        repository.delete(project)
    }

    fun setCurrentProject(project: Project) {
        _currentProject.value = project
    }

    fun clearCurrentProject() {
        _currentProject.value = Project(name = "", deadline = "", description = "")
    }
}
