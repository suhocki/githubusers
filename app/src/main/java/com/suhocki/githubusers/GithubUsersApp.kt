package com.suhocki.githubusers

import android.app.Application
import com.suhocki.githubusers.di.appModule
import com.suhocki.githubusers.di.localStorageModule
import com.suhocki.githubusers.di.remoteStorageModule
import org.koin.core.context.startKoin

class GithubUsersApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                appModule,
                remoteStorageModule,
                localStorageModule(this@GithubUsersApp)
            )
        }
    }
}