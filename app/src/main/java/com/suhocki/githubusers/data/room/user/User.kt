package com.suhocki.githubusers.data.room.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(
    @PrimaryKey val id: Int,
    val login: String,
    val url: String,
    val avatarUrl: String?,
)
