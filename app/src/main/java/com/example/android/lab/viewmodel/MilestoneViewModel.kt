package com.example.android.lab.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.lab.data.AppDatabase
import com.example.android.lab.data.Milestone
import com.example.android.lab.data.MilestoneRepository

import kotlinx.coroutines.launch

class MilestoneViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MilestoneRepository

    init {
        val milestoneDao = AppDatabase.getDatabase(application).milestoneDao()
        repository = MilestoneRepository(milestoneDao)
    }

    fun getMilestonesForProject(projectId: Int): LiveData<List<Milestone>> =
        repository.getMilestonesForProject(projectId)

    fun insert(milestone: Milestone) = viewModelScope.launch {
        repository.insert(milestone)
    }

    fun update(milestone: Milestone) = viewModelScope.launch {
        repository.update(milestone)
    }

    fun delete(milestone: Milestone) = viewModelScope.launch {
        repository.delete(milestone)
    }
}
