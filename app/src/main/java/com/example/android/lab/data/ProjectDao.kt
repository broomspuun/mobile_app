package com.example.android.lab.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(project: Project)

    @Update
    suspend fun update(project: Project)

    @Delete
    suspend fun delete(project: Project)

    @Query("SELECT * FROM projects ORDER BY deadline ASC")
    fun getAllProjects(): LiveData<List<Project>>
}
