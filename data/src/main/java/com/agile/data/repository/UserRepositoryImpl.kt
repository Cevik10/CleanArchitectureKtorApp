package com.agile.data.repository

import com.agile.data.api.ApiService
import com.agile.data.model.mapper.toUser
import com.agile.domain.model.User
import com.agile.data.model.mapper.toUserList
import com.agile.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override suspend fun getUsers(): List<User> {
        return apiService.getUsers().toUserList()
    }

    override suspend fun getUserById(id: Int): User {
        return apiService.getUserById(id).toUser()
    }
}