package com.aidan.dcard.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.aidan.dcard.domain.LoadDataFromAssetUseCase
import com.aidan.dcard.entity.RepoInfo
import com.aidan.dcard.infra.network.RepoInfoPagingSource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class MainViewModel(
    private val loadDataFromAssetUseCase: LoadDataFromAssetUseCase
): ViewModel(), KoinComponent {

    private val _repoInfoPagerFlow = MutableSharedFlow<Pager<Int, RepoInfo>>()
    val repoInfoPagerFlow = _repoInfoPagerFlow.asSharedFlow()

    fun loadLanguageColor(context: Context) {
        viewModelScope.launch {
            loadDataFromAssetUseCase.execute(context.assets.open("colors.json"))
        }
    }

    fun fetch(searchKey: String) {
        viewModelScope.launch {
            _repoInfoPagerFlow.emit(
                Pager(
                    config = PagingConfig(pageSize = 30, prefetchDistance = 2, initialLoadSize = 30),
                    initialKey = 1,
                    pagingSourceFactory = {
                        get<RepoInfoPagingSource> { parametersOf(searchKey) }
                    }
                )
            )
        }
    }
}