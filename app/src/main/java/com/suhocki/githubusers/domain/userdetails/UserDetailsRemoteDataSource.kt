package com.suhocki.githubusers.domain.userdetails

interface UserDetailsRemoteDataSource {
    suspend fun getUserDetails(url: String): UserDetails
}