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
import com.example.android.lab.databinding.FragmentTaskListBinding
import com.example.android.lab.ui.adapters.TaskAdapter
import com.example.android.lab.viewmodel.ProjectViewModel

class TaskListFragment : Fragment() {

    private val viewModel: ProjectViewModel by viewModels()
    private lateinit var binding: FragmentTaskListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskListBinding.inflate(inflater, container, false)

        val adapter = TaskAdapter {
            viewModel.setCurrentTask(it)
            findNavController().navigate(R.id.action_taskListFragment_to_taskDetailFragment)
        }

        binding.taskRecyclerView.adapter = adapter
        binding.taskRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.addTaskButton.setOnClickListener {
            viewModel.clearCurrentTask()
            findNavController().navigate(R.id.action_taskListFragment_to_taskDetailFragment)
        }

        viewModel.getAllTasks().observe(viewLifecycleOwner, Observer { tasks ->
            tasks?.let { adapter.submitList(it) }
        })

        return binding.root
    }
}
