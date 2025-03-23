package com.example.edbaacademyexample

import com.example.edbaacademyexample.models.Posts
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class NetworkManager {
    private val httpClient = HttpClient(CIO)

    suspend fun fetchPosts(): List<Posts> {
        val response =  httpClient
            .get(urlString = "https://jsonplaceholder.typicode.com/posts")
            .bodyAsText()

        return Json.decodeFromString<List<Posts>>(response)
    }
}