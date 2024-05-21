package com.example.android.lab.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lab.data.Project
import com.example.android.lab.databinding.ListItemProjectBinding

class ProjectAdapter(private val onClick: (Project) -> Unit) :
    ListAdapter<Project, ProjectAdapter.ProjectViewHolder>(ProjectDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = ListItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val project = getItem(position)
        holder.bind(project, onClick)
    }

    class ProjectViewHolder(private val binding: ListItemProjectBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(project: Project, onClick: (Project) -> Unit) {
            binding.project = project
            binding.root.setOnClickListener { onClick(project) }
            binding.executePendingBindings()
        }
    }
}

class ProjectDiffCallback : DiffUtil.ItemCallback<Project>() {
    override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem == newItem
    }
}

