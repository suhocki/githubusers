package com.suhocki.githubusers.domain.user

interface UserRemoteDataSource {
    suspend fun getUsers(url: String): Pair<List<User>, Page.Next?>
}