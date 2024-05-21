package com.example.android.lab.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM tasks WHERE projectId = :projectId")
    fun getTasksForProject(projectId: Int): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE milestoneId = :milestoneId")
    fun getTasksForMilestone(milestoneId: Int): LiveData<List<Task>>

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): LiveData<List<Task>>
}
