package com.example.android.lab.data

import androidx.lifecycle.LiveData

class MilestoneRepository(private val milestoneDao: MilestoneDao) {
    fun getMilestonesForProject(projectId: Int): LiveData<List<Milestone>> =
        milestoneDao.getMilestonesForProject(projectId)

    suspend fun insert(milestone: Milestone) {
        milestoneDao.insert(milestone)
    }

    suspend fun update(milestone: Milestone) {
        milestoneDao.update(milestone)
    }

    suspend fun delete(milestone: Milestone) {
        milestoneDao.delete(milestone)
    }
}
