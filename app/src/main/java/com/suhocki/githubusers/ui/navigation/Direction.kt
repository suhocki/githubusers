package com.suhocki.githubusers.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Direction {
    @Serializable
    object Users: Direction

    @Serializable
    class UserDetails(
        val url: String
    ): Direction
}