package com.example.android.lab.data

class ProjectRepository(private val projectDao: ProjectDao) {

    val allProjects = projectDao.getAllProjects()

    suspend fun insert(project: Project) {
        projectDao.insert(project)
    }

    suspend fun update(project: Project) {
        projectDao.update(project)
    }

    suspend fun delete(project: Project) {
        projectDao.delete(project)
    }
}
