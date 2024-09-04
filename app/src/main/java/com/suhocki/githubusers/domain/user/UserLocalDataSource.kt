package com.suhocki.githubusers.domain.user

import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    val users: Flow<List<User>>

    suspend fun addUsers(users: List<User>)

    suspend fun replaceAll(users: List<User>)
}