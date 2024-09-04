package com.suhocki.githubusers.di

import android.content.Context
import androidx.room.Room
import com.suhocki.githubusers.data.room.RoomDatabase
import com.suhocki.githubusers.data.room.RoomMapper
import com.suhocki.githubusers.data.room.user.UserDao
import com.suhocki.githubusers.data.room.user.UserRoomDataSource
import com.suhocki.githubusers.data.room.userdetails.UserDetailsDao
import com.suhocki.githubusers.data.room.userdetails.UserDetailsRoomDataSource
import com.suhocki.githubusers.domain.user.UserLocalDataSource
import com.suhocki.githubusers.domain.userdetails.UserDetailsLocalDataSource
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

fun localStorageModule(context: Context) = module {
    single {
        Room.databaseBuilder(
            context,
            RoomDatabase::class.java, "github-users-db"
        ).build()
    }

    factory<UserDao> { get<RoomDatabase>().userDao() }
    factory<UserLocalDataSource> { get<UserRoomDataSource>() }
    factory<UserDetailsDao> { get<RoomDatabase>().userDetailsDao() }
    factory<UserDetailsLocalDataSource> { get<UserDetailsRoomDataSource>() }

    factoryOf(::RoomMapper)
    factoryOf(::UserRoomDataSource)
    factoryOf(::UserDetailsRoomDataSource)
}
