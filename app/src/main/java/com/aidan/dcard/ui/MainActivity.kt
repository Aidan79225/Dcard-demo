package com.aidan.dcard.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aidan.dcard.R
import com.aidan.dcard.databinding.ActivityMainBinding
import com.aidan.dcard.entity.RepoInfo
import com.aidan.dcard.viewBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: AppCompatActivity() {
    private val vb by viewBinding(ActivityMainBinding::inflate)
    private val vm by viewModel<MainViewModel>()

    private val repoInfoAdapter by lazy { RepoInfoAdapter() }

    private var previousPagingJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.svRepo.apply {
            editText.setTextColor(ContextCompat.getColor(context, android.R.color.white))
            editText.setHintTextColor(ContextCompat.getColor(context, R.color.text_dark_gray))

            show()
            editText.addTextChangedListener {
                val searchKey = it?.takeIf { it.isNotEmpty() && it.isNotBlank() }?.toString() ?: return@addTextChangedListener
                vm.startSearchRepo(searchKey)
            }
        }
        vb.rvRepoInfo.apply {
            adapter = repoInfoAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        lifecycleScope.launch {
            vm.repoInfoPagerFlow.collect {
                subscribeNewPager(it)
            }
        }

        lifecycleScope.launch {
            repoInfoAdapter.loadStateFlow.collectLatest { loadStates ->
                vb.pbLoading.isVisible = loadStates.append is LoadState.Loading || loadStates.refresh is LoadState.Loading
                (loadStates.append as? LoadState.Error)?.also {
                    Snackbar.make(vb.svRepo, it.error.message ?: "Something went wrong", Snackbar.LENGTH_SHORT).show()
                }
                (loadStates.refresh as? LoadState.Error)?.also {
                    Snackbar.make(vb.svRepo, it.error.message ?: "Something went wrong", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
        vm.loadLanguageColor(this)
    }

    private fun subscribeNewPager(pager: Pager<Int, RepoInfo>) {
        previousPagingJob?.cancel()
        previousPagingJob = lifecycleScope.launch {
            pager.flow.collectLatest {
                repoInfoAdapter.submitData(it)
            }
        }
    }
}