package com.agile.ktorptoject.di


import android.content.Context
import android.util.Log
import com.agile.data.BuildConfig
import com.agile.data.api.ApiService
import com.agile.data.repository.UserLocalDataSourceImpl
import com.agile.data.repository.UserRemoteDataSourceImpl
import com.agile.data.repository.UserRepositoryImpl
import com.agile.domain.repository.UserLocalDataSource
import com.agile.domain.repository.UserRemoteDataSource
import com.agile.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            engine {
                connectTimeout = 10_000
                socketTimeout = 15_000
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = false
                    isLenient = true
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                    useAlternativeNames = false
                })
            }

            defaultRequest {
                headers.append("Accept", "application/json")
                headers.append("Content-Type", "application/json")
            }

            if (BuildConfig.DEBUG) {
                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            Log.d("KtorLogger", message)
                        }
                    }
                    level = LogLevel.INFO
                }
            }
        }
    }

    @Provides
    @Singleton
    fun provideApiService(client: HttpClient): ApiService {
        return ApiService(client)
    }

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(
        apiService: ApiService
    ): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(apiService)
    }


    @Provides
    @Singleton
    fun provideUserLocalDataSource(
        @ApplicationContext context: Context
    ): UserLocalDataSource {
        return UserLocalDataSourceImpl(context)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        remoteDataSource: UserRemoteDataSource,
        localDataSource: UserLocalDataSource
    ): UserRepository {
        return UserRepositoryImpl(remoteDataSource, localDataSource)
    }
}