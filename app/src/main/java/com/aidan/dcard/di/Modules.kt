package com.aidan.dcard.di

import androidx.room.Room
import com.aidan.dcard.database.MainDatabase
import com.aidan.dcard.domain.LoadDataFromAssetUseCase
import com.aidan.dcard.infra.network.GitHubService
import com.aidan.dcard.infra.network.RepoInfoPagingSource
import com.aidan.dcard.ui.MainViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit


val appModule = module {
    viewModelOf(::MainViewModel)
}

val utilModule = module {
    factory {
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }
}

val domainModule = module {
    factory { parametersHolder -> RepoInfoPagingSource(parametersHolder.get(), get(), get()) }
    factory { LoadDataFromAssetUseCase(get(), get()) }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }
    single {
        val contentType = "application/json".toMediaType()
        Retrofit.Builder().baseUrl("https://api.github.com/").client(get())
            .addConverterFactory(
                get<Json>().asConverterFactory(contentType)
            ).build()
    }
    single { get<Retrofit>().create(GitHubService::class.java) }
}

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            MainDatabase::class.java, "main.db"
        ).build()
    }
    single { get<MainDatabase>().provideLanguageColorDao() }
}