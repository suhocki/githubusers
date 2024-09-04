package com.suhocki.githubusers.data.room.userdetails

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDetailsDao {
    @Query("SELECT * FROM UserDetails WHERE detailsUrl = :url")
    fun getUserDetails(url: String): Flow<UserDetails?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(userDetails: UserDetails)
}