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

    @Query("SELECT * FROM tasks WHERE projectId = :projectId ORDER BY dueDate ASC")
    fun getTasksForProject(projectId: Long): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE milestoneId = :milestoneId ORDER BY dueDate ASC")
    fun getTasksForMilestone(milestoneId: Long): LiveData<List<Task>>

    @Query("SELECT * FROM tasks ORDER BY dueDate ASC")
    fun getAllTasks(): LiveData<List<Task>>
}
