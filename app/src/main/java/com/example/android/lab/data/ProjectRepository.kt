package com.example.android.lab.data

import androidx.lifecycle.LiveData

class ProjectRepository(private val projectDao: ProjectDao, private val milestoneDao: MilestoneDao, private val taskDao: TaskDao) {

    val allProjects: LiveData<List<Project>> = projectDao.getAllProjects()

    suspend fun insertProject(project: Project) {
        projectDao.insert(project)
    }

    suspend fun updateProject(project: Project) {
        projectDao.update(project)
    }

    suspend fun deleteProject(project: Project) {
        projectDao.delete(project)
    }

    fun getMilestonesForProject(projectId: Int): LiveData<List<Milestone>> {
        return milestoneDao.getMilestonesForProject(projectId)
    }

    suspend fun insertMilestone(milestone: Milestone) {
        milestoneDao.insert(milestone)
    }

    suspend fun updateMilestone(milestone: Milestone) {
        milestoneDao.update(milestone)
    }

    suspend fun deleteMilestone(milestone: Milestone) {
        milestoneDao.delete(milestone)
    }

    fun getTasksForProject(projectId: Int): LiveData<List<Task>> {
        return taskDao.getTasksForProject(projectId)
    }

    fun getTasksForMilestone(milestoneId: Int): LiveData<List<Task>> {
        return taskDao.getTasksForMilestone(milestoneId)
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return taskDao.getAllTasks()
    }

    suspend fun insertTask(task: Task) {
        taskDao.insert(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.update(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.delete(task)
    }
}
