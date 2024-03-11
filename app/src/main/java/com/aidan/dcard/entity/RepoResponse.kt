package com.aidan.dcard.entity

import kotlinx.serialization.Serializable

@Serializable
data class RepoResponse(
    val items: List<RepoInfo>
)