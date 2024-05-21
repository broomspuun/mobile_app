package com.example.android.lab.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.lab.data.Task
import com.example.android.lab.databinding.FragmentTaskDetailBinding
import com.example.android.lab.viewmodel.ProjectViewModel

class TaskDetailFragment : Fragment() {

    private lateinit var binding: FragmentTaskDetailBinding
    private lateinit var projectViewModel: ProjectViewModel

    private lateinit var taskNameEditText: EditText
    private lateinit var taskDescriptionEditText: EditText
    private lateinit var taskTypeEditText: EditText
    private lateinit var taskStatusEditText: EditText
    private lateinit var taskDeadlineEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        projectViewModel = ViewModelProvider(requireActivity())[ProjectViewModel::class.java]
        binding.viewModel = projectViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        taskNameEditText = binding.taskName
        taskDescriptionEditText = binding.taskDescription
        taskTypeEditText = binding.taskType
        taskStatusEditText = binding.taskStatus
        taskDeadlineEditText = binding.taskDeadline

        projectViewModel.currentTask.observe(viewLifecycleOwner) { task ->
            task?.let {
                taskNameEditText.setText(it.name)
                taskDescriptionEditText.setText(it.description)
                taskTypeEditText.setText(it.type)
                taskStatusEditText.setText(it.status)
                taskDeadlineEditText.setText(it.deadline)
            }
        }

        binding.saveTaskButton.setOnClickListener {
            val name = taskNameEditText.text.toString()
            val description = taskDescriptionEditText.text.toString()
            val type = taskTypeEditText.text.toString()
            val status = taskStatusEditText.text.toString()
            val deadline = taskDeadlineEditText.text.toString()

            val currentTask = projectViewModel.currentTask.value
            if (currentTask != null) {
                val updatedTask = currentTask.copy(name = name, description = description, type = type, status = status, deadline = deadline)
                if (currentTask.id == 0) {
                    projectViewModel.insertTask(updatedTask)
                } else {
                    projectViewModel.updateTask(updatedTask)
                }
            } else {
                val newTask = Task(projectId = projectViewModel.currentProject.value?.id ?: 0, milestoneId = projectViewModel.currentMilestone.value?.id ?: 0, name = name, description = description, type = type, status = status, deadline = deadline)
                projectViewModel.insertTask(newTask)
            }
            findNavController().navigateUp()
        }
    }
}
