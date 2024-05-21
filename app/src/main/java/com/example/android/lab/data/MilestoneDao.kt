package com.example.android.lab.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MilestoneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(milestone: Milestone)

    @Update
    suspend fun update(milestone: Milestone)

    @Delete
    suspend fun delete(milestone: Milestone)

    @Query("SELECT * FROM milestones WHERE projectId = :projectId")
    fun getMilestonesForProject(projectId: Int): LiveData<List<Milestone>>

    @Query("SELECT * FROM milestones WHERE id = :id")
    suspend fun getMilestoneById(id: Int): Milestone
}



