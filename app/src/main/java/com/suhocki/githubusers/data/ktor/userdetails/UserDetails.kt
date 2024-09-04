package com.suhocki.githubusers.data.ktor.userdetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class UserDetails(
    val login: String,
    @SerialName("avatar_url") val avatarUrl: String,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val bio: String?,
    @SerialName("twitter_username") val twitterUsername: String?,
    @SerialName("public_repos") val publicRepos: Int,
    @SerialName("public_gists") val publicGists: Int,
    val followers: Int,
    val following: Int,
)