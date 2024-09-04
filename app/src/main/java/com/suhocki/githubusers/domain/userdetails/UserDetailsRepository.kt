package com.suhocki.githubusers.domain.userdetails

import kotlinx.coroutines.flow.Flow

class UserDetailsRepository(
    private val localUserDataSource: UserDetailsLocalDataSource,
    private val remoteUserDataSource: UserDetailsRemoteDataSource,
) {
    fun getUserDetails(url: String): Flow<UserDetails> {
        return localUserDataSource.getUserDetails(url)
    }

    suspend fun refresh(url: String) {
        val userDetails = remoteUserDataSource.getUserDetails(url)
        localUserDataSource.update(userDetails, url)
    }
}