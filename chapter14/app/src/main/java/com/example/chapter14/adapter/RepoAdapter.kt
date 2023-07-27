package com.example.chapter14.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter14.databinding.ItemRepoBinding
import com.example.chapter14.model.Repo

class RepoAdapter(
    private val onClick: (Repo) -> Unit
): ListAdapter<Repo, RepoAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemRepoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: Repo) {
            binding.repoNameTextView.text = repo.name
            binding.repoDescriptionTextView.text = repo.description
            binding.starCountTextView.text = repo.starCount.toString()
            binding.forkCountTextView.text = repo.forkCount.toString()

            binding.root.setOnClickListener {
                onClick(repo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }
        }
    }
}