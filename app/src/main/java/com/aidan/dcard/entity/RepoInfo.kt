package com.aidan.dcard.entity

import com.aidan.dcard.util.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class RepoInfo(
    val id: Int,
    @SerialName("full_name")
    val fullName: String,
    val description: String = "",
    val owner: UserInfo,
    val topics: List<String>,
    @SerialName("stargazers_count")
    val star: Int,
    val language: String = "",
    val languageColor: String = "#2F81F7",
    @Serializable(with = DateSerializer::class)
    @SerialName("pushed_at")
    val updatedAt: Date?
)