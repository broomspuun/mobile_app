package com.example.android.lab.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.lab.R
import com.example.android.lab.databinding.FragmentMilestoneListBinding
import com.example.android.lab.ui.adapters.MilestoneAdapter
import com.example.android.lab.viewmodel.ProjectViewModel

class MilestoneListFragment : Fragment() {

    private val viewModel: ProjectViewModel by viewModels()
    private lateinit var binding: FragmentMilestoneListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMilestoneListBinding.inflate(inflater, container, false)

        val adapter = MilestoneAdapter {
            viewModel.setCurrentMilestone(it)
            findNavController().navigate(R.id.action_milestoneListFragment_to_milestoneDetailFragment)
        }

        binding.milestoneRecyclerView.adapter = adapter
        binding.milestoneRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.addMilestoneButton.setOnClickListener {
            viewModel.clearCurrentMilestone()
            findNavController().navigate(R.id.action_milestoneListFragment_to_milestoneDetailFragment)
        }

        viewModel.currentProject.observe(viewLifecycleOwner, Observer { project ->
            project?.let {
                viewModel.getMilestonesForProject(it.id).observe(viewLifecycleOwner, Observer { milestones ->
                    milestones?.let { adapter.submitList(it) }
                })
            }
        })

        return binding.root
    }
}


