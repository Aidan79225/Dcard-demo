package com.aidan.dcard

import org.koin.dsl.module
import retrofit2.Retrofit




val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()
    }

    single {
        get<Retrofit>().create(GitHubService::class.java)
    }

}