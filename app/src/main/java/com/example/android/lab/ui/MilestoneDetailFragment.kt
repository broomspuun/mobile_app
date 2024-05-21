package com.example.android.lab.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.lab.data.Milestone
import com.example.android.lab.databinding.FragmentMilestoneDetailBinding
import com.example.android.lab.viewmodel.ProjectViewModel

class MilestoneDetailFragment : Fragment() {

    private lateinit var binding: FragmentMilestoneDetailBinding
    private lateinit var projectViewModel: ProjectViewModel

    private lateinit var milestoneNameEditText: EditText
    private lateinit var milestoneDeadlineEditText: EditText
    private lateinit var milestoneDescriptionEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMilestoneDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        projectViewModel = ViewModelProvider(requireActivity())[ProjectViewModel::class.java]
        binding.viewModel = projectViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        milestoneNameEditText = binding.milestoneName
        milestoneDeadlineEditText = binding.milestoneDeadline
        milestoneDescriptionEditText = binding.milestoneDescription

        projectViewModel.currentMilestone.observe(viewLifecycleOwner) { milestone ->
            milestone?.let {
                milestoneNameEditText.setText(it.name)
                milestoneDeadlineEditText.setText(it.deadline)
                milestoneDescriptionEditText.setText(it.description)
            }
        }

        binding.saveMilestoneButton.setOnClickListener {
            val name = milestoneNameEditText.text.toString()
            val deadline = milestoneDeadlineEditText.text.toString()
            val description = milestoneDescriptionEditText.text.toString()

            val currentMilestone = projectViewModel.currentMilestone.value
            if (currentMilestone != null) {
                val updatedMilestone = currentMilestone.copy(name = name, deadline = deadline, description = description)
                if (currentMilestone.id == 0) {
                    projectViewModel.insertMilestone(updatedMilestone)
                } else {
                    projectViewModel.updateMilestone(updatedMilestone)
                }
            } else {
                val newMilestone = Milestone(projectId = projectViewModel.currentProject.value?.id ?: 0, name = name, deadline = deadline, description = description)
                projectViewModel.insertMilestone(newMilestone)
            }
            findNavController().navigateUp()
        }
    }
}
