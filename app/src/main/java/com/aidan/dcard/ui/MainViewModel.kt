package com.aidan.dcard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidan.dcard.infra.GitHubService
import com.aidan.dcard.entity.RepoInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val gitHubService: GitHubService
): ViewModel() {
    private val _repoInfoFlow = MutableStateFlow(emptyList<RepoInfo>())
    val repoInfoFlow = _repoInfoFlow.asStateFlow()

    fun fetch() {
        viewModelScope.launch {
            _repoInfoFlow.value = gitHubService.listRepos("q", 1).items
        }
    }
}