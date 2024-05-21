package com.example.android.lab.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lab.data.Milestone
import com.example.android.lab.databinding.ListItemMilestoneBinding

class MilestoneAdapter(private val onClick: (Milestone) -> Unit) :
    ListAdapter<Milestone, MilestoneAdapter.MilestoneViewHolder>(MilestoneDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MilestoneViewHolder {
        val binding = ListItemMilestoneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MilestoneViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MilestoneViewHolder, position: Int) {
        val milestone = getItem(position)
        holder.bind(milestone, onClick)
    }

    class MilestoneViewHolder(private val binding: ListItemMilestoneBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(milestone: Milestone, onClick: (Milestone) -> Unit) {
            binding.milestone = milestone
            binding.root.setOnClickListener { onClick(milestone) }
            binding.executePendingBindings()
        }
    }
}

class MilestoneDiffCallback : DiffUtil.ItemCallback<Milestone>() {
    override fun areItemsTheSame(oldItem: Milestone, newItem: Milestone): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Milestone, newItem: Milestone): Boolean {
        return oldItem == newItem
    }
}

