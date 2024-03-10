package com.aidan.dcard.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val id: Int,
    val name: String
)