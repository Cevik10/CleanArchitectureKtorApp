package com.agile.data.di

import android.util.Log
import com.agile.data.BuildConfig
import com.agile.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.utils.EmptyContent.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            // Zaman aşımı ayarları (optimizasyon için önemli)
            engine {
                connectTimeout = 10_000
                socketTimeout = 15_000
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = false // gereksiz ise kapat (performans)
                    isLenient = true
                    ignoreUnknownKeys = true
                    coerceInputValues = true // Eksik alanlarda default değer kullan
                    useAlternativeNames = false // serialize name fallback'lerini devre dışı bırak
                })
            }

            // Varsayılan request config (istek başına tekrar yazmayı önler)
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
                    level = LogLevel.ALL
                }
            }
        }
    }

}