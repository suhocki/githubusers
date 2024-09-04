package com.suhocki.githubusers.di

import com.suhocki.githubusers.domain.user.UsersRepository
import com.suhocki.githubusers.domain.userdetails.UserDetailsRepository
import com.suhocki.githubusers.ui.userdetails.UserDetailsViewModel
import com.suhocki.githubusers.ui.users.UsersViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    factoryOf(::UsersRepository)
    factoryOf(::UserDetailsRepository)

    viewModelOf(::UsersViewModel)
    viewModelOf(::UserDetailsViewModel)
}