package com.aidan.dcard

import com.aidan.dcard.entity.RepoInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GitHubService {
    @Headers(
        """
        Accept: application/vnd.github.v3.full+json,
        X-GitHub-Api-Version: 2022-11-28"   
        """
     )
    @GET("search/repositories")
    fun listRepos(
        @Query("q") searchKey: String,
        @Query("page") page: Int,
    ): Call<List<RepoInfo>>
}