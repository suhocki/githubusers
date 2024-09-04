package com.suhocki.githubusers.data.room.userdetails

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserDetails(
    @PrimaryKey val detailsUrl: String,
    val login: String,
    val avatarUrl: String?,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val bio: String?,
    val twitterUsername: String?,
    val publicRepos: Int,
    val publicGists: Int,
    val followers: Int,
    val following: Int,
)