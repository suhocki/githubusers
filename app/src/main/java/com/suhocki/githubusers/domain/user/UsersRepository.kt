package com.suhocki.githubusers.domain.user

import kotlinx.coroutines.flow.Flow

class UsersRepository(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
) {
    private var nextPage: Page = Page.First("https://api.github.com/users")

    val users: Flow<List<User>> = userLocalDataSource.users

    suspend fun refreshNextPage() {
        val (users, nextPage) = userRemoteDataSource.getUsers(nextPage.url)

        if (this.nextPage is Page.First) {
            userLocalDataSource.replaceAll(users)
        } else {
            userLocalDataSource.addUsers(users)
        }

        if (nextPage != null) {
            this.nextPage = nextPage
        }
    }
}