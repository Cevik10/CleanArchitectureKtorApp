package com.agile.data.repository


import android.util.Log
import com.agile.domain.model.User
import com.agile.domain.repository.UserLocalDataSource
import com.agile.domain.repository.UserRemoteDataSource
import com.agile.domain.repository.UserRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {

    companion object {
        private const val FETCH_INTERVAL_MS = 5_000 // 5 seconds
    }

    override suspend fun getUsers(): List<User> {
        val lastFetchTime = localDataSource.getLastFetchTime().first()
        val currentTime = System.currentTimeMillis()

        return if (currentTime - lastFetchTime > FETCH_INTERVAL_MS) {
            Log.d("KtorLogger", "Remote data")
            val users = remoteDataSource.getUsers()
            localDataSource.saveUsers(users)
            localDataSource.saveLastFetchTime(currentTime)
            users
        } else {
            Log.d("KtorLogger", "Locale data")
            localDataSource.getUsers()
        }
    }

    override suspend fun getUserById(id: Int): User {
        val localUser = localDataSource.getUserById(id)
        return if (localUser != null) {
            localUser
        } else {
            val user = remoteDataSource.getUserById(id)
            localDataSource.saveUser(user)
            user
        }
    }

    override suspend fun refreshUsersFromRemote(): List<User> {
        val currentTime = System.currentTimeMillis()
        val users = remoteDataSource.getUsers()
        localDataSource.saveUsers(users)
        localDataSource.saveLastFetchTime(currentTime)
        Log.d("KtorLogger", "Remote data (force refresh)")
        return users
    }
}