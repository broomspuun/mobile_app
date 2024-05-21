package com.example.android.lab.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.android.lab.data.*
import kotlinx.coroutines.launch

class ProjectViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProjectRepository
    val allProjects: LiveData<List<Project>>

    private val _currentProject = MutableLiveData<Project>()
    val currentProject: LiveData<Project> get() = _currentProject

    private val _currentMilestone = MutableLiveData<Milestone>()
    val currentMilestone: LiveData<Milestone> get() = _currentMilestone

    private val _currentTask = MutableLiveData<Task>()
    val currentTask: LiveData<Task> get() = _currentTask

    init {
        val database = AppDatabase.getDatabase(application)
        val projectDao = database.projectDao()
        val milestoneDao = database.milestoneDao()
        val taskDao = database.taskDao()
        repository = ProjectRepository(projectDao, milestoneDao, taskDao)
        allProjects = repository.allProjects
    }

    fun insertProject(project: Project) = viewModelScope.launch {
        repository.insertProject(project)
    }

    fun updateProject(project: Project) = viewModelScope.launch {
        repository.updateProject(project)
    }

    fun deleteProject(project: Project) = viewModelScope.launch {
        repository.deleteProject(project)
    }

    fun setCurrentProject(project: Project) {
        _currentProject.value = project
    }

    fun clearCurrentProject() {
        _currentProject.value = Project(name = "", deadline = "", description = "")
    }

    fun setCurrentMilestone(milestone: Milestone) {
        _currentMilestone.value = milestone
    }

    fun clearCurrentMilestone() {
        _currentMilestone.value = Milestone(projectId = 0, name = "", deadline = "", description = "")
    }

    fun insertMilestone(milestone: Milestone) = viewModelScope.launch {
        repository.insertMilestone(milestone)
    }

    fun updateMilestone(milestone: Milestone) = viewModelScope.launch {
        repository.updateMilestone(milestone)
    }

    fun getMilestonesForProject(projectId: Int): LiveData<List<Milestone>> {
        return repository.getMilestonesForProject(projectId)
    }

    fun setCurrentTask(task: Task) {
        _currentTask.value = task
    }

    fun clearCurrentTask() {
        _currentTask.value = Task(projectId = 0, milestoneId = 0, name = "", description = "", type = "", status = "", deadline = "")
    }

    fun insertTask(task: Task) = viewModelScope.launch {
        repository.insertTask(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        repository.updateTask(task)
    }

    fun getTasksForMilestone(milestoneId: Int): LiveData<List<Task>> {
        return repository.getTasksForMilestone(milestoneId)
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return repository.getAllTasks()
    }
}




