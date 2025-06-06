package com.agile.data.api


import com.agile.data.model.UserDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ApiService @Inject constructor() {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getUsers(): List<UserDto> {
        return client.get("https://jsonplaceholder.typicode.com/users").body()
    }

    suspend fun getUserById(id: Int): UserDto {
        return client.get("https://jsonplaceholder.typicode.com/users/$id").body()
    }
}