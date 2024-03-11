package com.aidan.dcard.infra

import com.aidan.dcard.entity.RepoInfo
import com.aidan.dcard.entity.RepoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface GitHubService {

    @GET("search/repositories")
    suspend fun listRepos(
        @Query("q") searchKey: String,
        @Query("page") page: Int
    ): RepoResponse
}