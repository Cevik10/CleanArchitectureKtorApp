package com.agile.data.api


import com.agile.data.api.ApiConstants.BASE_URL
import com.agile.data.model.UserDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class ApiService @Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun getUsers(): List<UserDto> {
        return httpClient.get("$BASE_URL/users").body()
    }

    suspend fun getUserById(id: Int): UserDto {
        return httpClient.get("$BASE_URL/users/$id").body()
    }
}

object ApiConstants {
    const val BASE_URL = "https://jsonplaceholder.typicode.com"
}
