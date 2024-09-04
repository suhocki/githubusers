package com.suhocki.githubusers.data.ktor

import com.suhocki.githubusers.data.ktor.user.UserApi
import com.suhocki.githubusers.data.ktor.userdetails.UserDetailsApi
import com.suhocki.githubusers.domain.user.Page
import com.suhocki.githubusers.domain.user.User
import com.suhocki.githubusers.domain.user.UserRemoteDataSource
import com.suhocki.githubusers.domain.userdetails.UserDetails
import com.suhocki.githubusers.domain.userdetails.UserDetailsRemoteDataSource

class KtorService(
    private val userApi: UserApi,
    private val userDetailsApi: UserDetailsApi,
    private val ktorMapper: KtorMapper,
) : UserRemoteDataSource, UserDetailsRemoteDataSource {

    override suspend fun getUsers(url: String): Pair<List<User>, Page.Next?> {
        println("fetching users: " + url)
        return userApi.getUsers(url).let { (users, nextPage) ->
            users.map(ktorMapper::map) to nextPage
        }
    }

    override suspend fun getUserDetails(url: String): UserDetails {
        println("fetching details: " + url)
        return userDetailsApi.getUserDetails(url).let(ktorMapper::map)
    }
}