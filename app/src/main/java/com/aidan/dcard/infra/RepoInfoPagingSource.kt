package com.aidan.dcard.infra

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aidan.dcard.entity.RepoInfo

class RepoInfoPagingSource(
    private val searchKey: String,
    private val gitHubService: GitHubService
): PagingSource<Int, RepoInfo>() {
    override fun getRefreshKey(state: PagingState<Int, RepoInfo>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoInfo> {
        return try {
            val pageNumber = params.key ?: 1
            val listItems = gitHubService.listRepos(searchKey, pageNumber).items
            val prevKey = if (pageNumber > 1) pageNumber - 1 else null
            val nextKey = if (listItems.isNotEmpty()) pageNumber + 1 else null

            Log.d("RepoInfoPagingSource", "succeed")
            LoadResult.Page(
                data = listItems,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            Log.d("RepoInfoPagingSource", "failed ${e}")
            LoadResult.Error(e)
        }
    }
}