package com.suhocki.githubusers.di

import com.suhocki.githubusers.data.ktor.KtorMapper
import com.suhocki.githubusers.data.ktor.KtorService
import com.suhocki.githubusers.data.ktor.user.UserApi
import com.suhocki.githubusers.data.ktor.userdetails.UserDetailsApi
import com.suhocki.githubusers.domain.user.UserRemoteDataSource
import com.suhocki.githubusers.domain.userdetails.UserDetailsRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val remoteStorageModule = module {
    single { HttpClient(Android) }
    single<Json> { Json { ignoreUnknownKeys = true } }

    factory<UserRemoteDataSource> { get<KtorService>() }
    factory<UserDetailsRemoteDataSource> { get<KtorService>() }

    factoryOf(::UserApi)
    factoryOf(::UserDetailsApi)
    factoryOf(::KtorService)
    factoryOf(::KtorMapper)
}