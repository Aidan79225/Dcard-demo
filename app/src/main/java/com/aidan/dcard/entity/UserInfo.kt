package com.aidan.dcard.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val id: Int,
    @SerialName("login")
    val name: String,
    @SerialName("avatar_url")
    val avatarUrl: String
)