package com.suhocki.githubusers.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.suhocki.githubusers.data.room.user.User
import com.suhocki.githubusers.data.room.user.UserDao
import com.suhocki.githubusers.data.room.userdetails.UserDetails
import com.suhocki.githubusers.data.room.userdetails.UserDetailsDao

@Database(
    entities = [
        User::class,
        UserDetails::class,
    ],
    version = 1
)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun userDetailsDao(): UserDetailsDao
}