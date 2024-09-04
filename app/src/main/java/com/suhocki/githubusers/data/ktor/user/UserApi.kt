package com.suhocki.githubusers.data.ktor.user

import com.suhocki.githubusers.domain.user.Page
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class UserApi(
    private val client: HttpClient,
    private val json: Json
) {
    suspend fun getUsers(pageUri: String): Pair<List<User>, Page.Next?> {
        val response = client.get(pageUri)
        val nextPageUri = response.headers["Link"]?.parseLinkHeader()
        val users = json.decodeFromString<List<User>>(response.bodyAsText())
        return users to nextPageUri?.let(Page::Next)
    }

    private fun String.parseLinkHeader(): String? {
        split(", ").forEach { entry ->
            val (uri, rel) = entry.split("; ")
            if (rel.contains("next")) {
                return uri.removeSurrounding("<", ">")
            }
        }
        return null
    }
}