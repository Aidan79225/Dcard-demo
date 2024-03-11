package com.aidan.dcard.ui

import androidx.recyclerview.widget.DiffUtil
import com.aidan.dcard.entity.RepoInfo

class RepoInfoItemCallback: DiffUtil.ItemCallback<RepoInfo>() {
    override fun areItemsTheSame(oldItem: RepoInfo, newItem: RepoInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RepoInfo, newItem: RepoInfo): Boolean {
        return oldItem == newItem
    }
}