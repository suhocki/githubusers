package com.suhocki.githubusers.data.room.user

import com.suhocki.githubusers.data.room.RoomMapper
import com.suhocki.githubusers.domain.user.User
import com.suhocki.githubusers.domain.user.UserLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRoomDataSource(
    private val userDao: UserDao,
    private val roomMapper: RoomMapper,
) : UserLocalDataSource {

    override val users: Flow<List<User>> =
        userDao.getAll().map { data -> data.map(roomMapper::map) }

    override suspend fun addUsers(users: List<User>) {
        userDao.insertAll(users.map(roomMapper::map))
    }

    override suspend fun replaceAll(users: List<User>) {
        userDao.replaceAll(users.map(roomMapper::map))
    }
}