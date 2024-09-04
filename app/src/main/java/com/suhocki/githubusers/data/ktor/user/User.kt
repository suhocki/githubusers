package com.suhocki.githubusers.data.ktor.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class User(
    val id: Int,
    val login: String,
    val url: String,
    @SerialName("avatar_url") val avatarUrl: String?,
)
