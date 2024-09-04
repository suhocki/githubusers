package com.suhocki.githubusers.data.room.userdetails

import com.suhocki.githubusers.data.room.RoomMapper
import com.suhocki.githubusers.domain.userdetails.UserDetails
import com.suhocki.githubusers.domain.userdetails.UserDetailsLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

class UserDetailsRoomDataSource(
    private val userDetailsDao: UserDetailsDao,
    private val roomMapper: RoomMapper,
) : UserDetailsLocalDataSource {
    override fun getUserDetails(url: String): Flow<UserDetails> {
        return userDetailsDao.getUserDetails(url)
            .filterNotNull()
            .map(roomMapper::map)
    }

    override suspend fun update(userDetails: UserDetails, url: String) {
        userDetailsDao.update(roomMapper.map(userDetails, url))
    }
}