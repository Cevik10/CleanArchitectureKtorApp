package com.agile.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.agile.data.model.UserDto
import com.agile.data.model.mapper.toUserDtoList
import com.agile.data.model.mapper.toUserList
import com.agile.domain.model.User
import com.agile.domain.repository.UserLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val context: Context
) : UserLocalDataSource {

    companion object {
        private val USERS_KEY = stringPreferencesKey("users")
        private val LAST_FETCH_TIME_KEY = longPreferencesKey("last_fetch_time")

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")
    }

    private val dataStore: DataStore<Preferences>
        get() = context.dataStore

    override suspend fun saveUsers(users: List<User>) {
        dataStore.edit { preferences ->
            preferences[USERS_KEY] = Json.encodeToString(users.toUserDtoList())
        }
    }

    override suspend fun getUsers(): List<User> {
        return dataStore.data
            .map { preferences ->
                preferences[USERS_KEY]?.let {
                    Json.decodeFromString<List<UserDto>>(it).toUserList()
                } ?: emptyList()
            }
            .first()
    }

    override suspend fun saveUser(user: User) {
        val users = getUsers().toMutableList().apply {
            removeAll { it.id == user.id }
            add(user)
        }
        saveUsers(users)
    }

    override suspend fun getUserById(id: Int): User? {
        return getUsers().find { it.id == id }
    }

    override suspend fun getLastFetchTime(): Flow<Long> {
        return dataStore.data.map { preferences ->
            preferences[LAST_FETCH_TIME_KEY] ?: 0L
        }
    }

    override suspend fun saveLastFetchTime(time: Long) {
        dataStore.edit { preferences ->
            preferences[LAST_FETCH_TIME_KEY] = time
        }
    }
}