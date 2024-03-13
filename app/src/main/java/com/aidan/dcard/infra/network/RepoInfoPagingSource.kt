package com.aidan.dcard.infra.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aidan.dcard.infra.database.LanguageColorDao
import com.aidan.dcard.entity.RepoInfo

class RepoInfoPagingSource(
    private val searchKey: String,
    private val gitHubService: GitHubService,
    private val dao: LanguageColorDao
) : PagingSource<Int, RepoInfo>() {
    override fun getRefreshKey(state: PagingState<Int, RepoInfo>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoInfo> {
        return try {
            val pageNumber = params.key ?: 1
            val listItems = gitHubService.listRepos(searchKey, pageNumber).items
            val prevKey = if (pageNumber > 1) pageNumber - 1 else null
            val nextKey = if (listItems.isNotEmpty()) pageNumber + 1 else null

            val languageColorMap =
                dao.findByNames(
                    listItems.map { it.language }.toSet()
                ).associate {
                    it.name to it.color
                }

            LoadResult.Page(
                data = listItems.map {
                    it.copy(
                        languageColor = languageColorMap[it.language] ?: it.languageColor
                    )
                },
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}