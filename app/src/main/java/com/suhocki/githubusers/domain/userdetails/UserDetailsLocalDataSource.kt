package com.suhocki.githubusers.domain.userdetails

import kotlinx.coroutines.flow.Flow

interface UserDetailsLocalDataSource {
    fun getUserDetails(url: String): Flow<UserDetails>

    suspend fun update(userDetails: UserDetails, url: String)
}