package com.agile.domain.repository

import com.agile.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
    suspend fun getUserById(id: Int): User
}