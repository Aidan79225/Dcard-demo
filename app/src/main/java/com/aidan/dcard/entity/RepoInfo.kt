package com.aidan.dcard.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepoInfo(
    val id: Int,
    @SerialName("full_name")
    val fullName: String,
    val description: String,
    val owner: UserInfo,
    val topics: List<String>
)