package com.aidan.dcard.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aidan.dcard.R
import com.aidan.dcard.databinding.ItemRepoBinding
import com.aidan.dcard.entity.RepoInfo
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip


class RepoInfoAdapter: ListAdapter<RepoInfo, RepoViewHolder>(RepoInfoItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        Log.d("RepoViewHolder", "onCreateViewHolder")
        return RepoViewHolder(
            ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        Log.d("RepoViewHolder", position.toString())
        holder.onBind(getItem(position))
    }
}

class RepoViewHolder(
    private val vb: ItemRepoBinding
): RecyclerView.ViewHolder(vb.root) {
    fun onBind(repoInfo: RepoInfo) {
        vb.tvUserName.text = repoInfo.owner.name
        vb.tvDescription.text = repoInfo.description
        vb.cgTopic.removeAllViews()
        vb.cgTopic.isVisible = repoInfo.topics.isNotEmpty()
        repoInfo.topics.take(5).forEach {
            vb.cgTopic.addView(
                createTopicChip(it)
            )
        }
        Glide.with(vb.ivAvatar).clear(vb.ivAvatar)
        Glide.with(vb.ivAvatar).load(repoInfo.owner.avatarUrl)
            .circleCrop()
            .into(vb.ivAvatar)
    }

    private fun createTopicChip(topic: String) = Chip(vb.root.context).apply {
        text = topic
        setTextColor(ContextCompat.getColor(context, R.color.text_light_blue))
        setChipBackgroundColorResource(R.color.bg_dark_blue)
    }
}