package com.suhocki.githubusers.domain.user

sealed class Page(val url: String) {

    class Next(nextUrl: String) : Page(nextUrl)

    class First(initialUrl: String) : Page(initialUrl)
}
