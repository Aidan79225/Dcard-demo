package com.aidan.dcard.ui

import android.graphics.drawable.LayerDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aidan.dcard.R
import com.aidan.dcard.databinding.ItemRepoBinding
import com.aidan.dcard.entity.RepoInfo
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import org.intellij.lang.annotations.Language


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
        vb.apply {
            tvUserName.text = repoInfo.owner.name
            tvDescription.text = repoInfo.description
            cgTopic.removeAllViews()
            cgTopic.isVisible = repoInfo.topics.isNotEmpty()
            repoInfo.topics.take(5).forEach {
                cgTopic.addView(
                    createTopicChip(it)
                )
            }
            Glide.with(ivAvatar).clear(ivAvatar)
            Glide.with(ivAvatar).load(repoInfo.owner.avatarUrl)
                .circleCrop()
                .into(ivAvatar)
            tvStarCount.text = repoInfo.star.toString()

            updateLanguageLayout(repoInfo.language)
        }
    }

    private fun updateLanguageLayout(language: String) = with(vb) {
        (ivLanguage.drawable as? LayerDrawable)?.findDrawableByLayerId(R.id.mainLayer)?.apply {
            DrawableCompat.setTint(mutate(), ContextCompat.getColor(itemView.context, R.color.text_light_blue))
        }
        tvLanguage.text = language
    }

    private fun createTopicChip(topic: String) = Chip(vb.root.context).apply {
        text = topic
        setTextColor(ContextCompat.getColor(context, R.color.text_light_blue))
        setChipBackgroundColorResource(R.color.bg_dark_blue)
    }
}