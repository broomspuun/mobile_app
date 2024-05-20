package com.example.android.lab.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.android.lab.R
import com.example.android.lab.databinding.FragmentProjectListBinding
import com.example.android.lab.ui.adapters.ProjectAdapter
import com.example.android.lab.viewmodel.ProjectViewModel

class ProjectListFragment : Fragment() {

    private val viewModel: ProjectViewModel by viewModels()
    private lateinit var binding: FragmentProjectListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProjectListBinding.inflate(inflater, container, false)

        val adapter = ProjectAdapter {
            viewModel.setCurrentProject(it)
            findNavController().navigate(R.id.action_projectListFragment_to_projectDetailFragment)
        }

        binding.projectRecyclerView.adapter = adapter
        binding.addProjectButton.setOnClickListener {
            viewModel.clearCurrentProject()
            findNavController().navigate(R.id.action_projectListFragment_to_projectDetailFragment)
        }

        viewModel.allProjects.observe(viewLifecycleOwner, Observer { projects ->
            projects?.let { adapter.submitList(it) }
        })

        return binding.root
    }
}
