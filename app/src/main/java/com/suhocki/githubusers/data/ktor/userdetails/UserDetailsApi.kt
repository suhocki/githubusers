package com.suhocki.githubusers.data.ktor.userdetails

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class UserDetailsApi(
    private val client: HttpClient,
    private val json: Json
) {
    suspend fun getUserDetails(url: String): UserDetails {
        val response = client.get(url)
        return json.decodeFromString(response.bodyAsText())
    }
}