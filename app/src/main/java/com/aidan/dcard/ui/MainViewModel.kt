package com.aidan.dcard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.aidan.dcard.infra.GitHubService
import com.aidan.dcard.entity.RepoInfo
import com.aidan.dcard.infra.RepoInfoPagingSource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.getKoin

class MainViewModel(
    private val gitHubService: GitHubService
): ViewModel() {

    private val _repoInfoPagerFlow = MutableSharedFlow<Pager<Int, RepoInfo>>()
    val repoInfoPagerFlow = _repoInfoPagerFlow.asSharedFlow()

    fun fetch(searchKey: String) {
        viewModelScope.launch {
            _repoInfoPagerFlow.emit(
                Pager(
                    config = PagingConfig(pageSize = 30, prefetchDistance = 2, initialLoadSize = 30),
                    initialKey = 1,
                    pagingSourceFactory = {
                        getKoin().get<RepoInfoPagingSource> { parametersOf(searchKey) }
                    }
                )
            )
        }
    }
}