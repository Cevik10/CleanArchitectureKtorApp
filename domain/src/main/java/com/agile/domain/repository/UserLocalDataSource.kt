package com.agile.domain.repository

import com.agile.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    suspend fun saveUsers(users: List<User>)
    suspend fun getUsers(): List<User>
    suspend fun saveUser(user: User)
    suspend fun getUserById(id: Int): User?
    suspend fun getLastFetchTime(): Flow<Long>
    suspend fun saveLastFetchTime(time: Long)
}