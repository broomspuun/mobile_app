package com.example.android.lab.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.lab.data.Project
import com.example.android.lab.databinding.FragmentProjectDetailBinding
import com.example.android.lab.viewmodel.ProjectViewModel

class ProjectDetailFragment : Fragment() {

    private lateinit var binding: FragmentProjectDetailBinding
    private lateinit var projectViewModel: ProjectViewModel

    private lateinit var projectNameEditText: EditText
    private lateinit var projectDeadlineEditText: EditText
    private lateinit var projectDescriptionEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProjectDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        projectViewModel = ViewModelProvider(requireActivity())[ProjectViewModel::class.java]
        binding.viewModel = projectViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        projectNameEditText = binding.projectName
        projectDeadlineEditText = binding.projectDeadline
        projectDescriptionEditText = binding.projectDescription

        projectViewModel.currentProject.observe(viewLifecycleOwner) { project ->
            project?.let {
                projectNameEditText.setText(it.name)
                projectDeadlineEditText.setText(it.deadline)
                projectDescriptionEditText.setText(it.description)
            }
        }

        binding.saveProjectButton.setOnClickListener {
            val name = projectNameEditText.text.toString()
            val deadline = projectDeadlineEditText.text.toString()
            val description = projectDescriptionEditText.text.toString()

            val currentProject = projectViewModel.currentProject.value
            if (currentProject != null) {
                val updatedProject = currentProject.copy(name = name, deadline = deadline, description = description)
                if (currentProject.id == 0) {
                    projectViewModel.insertProject(updatedProject)
                } else {
                    projectViewModel.updateProject(updatedProject)
                }
            } else {
                val newProject = Project(name = name, deadline = deadline, description = description)
                projectViewModel.insertProject(newProject)
            }
            findNavController().navigateUp()
        }
    }
}
