package com.aidan.dcard.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aidan.dcard.databinding.ActivityMainBinding
import com.aidan.dcard.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: AppCompatActivity() {
    private val vb by viewBinding(ActivityMainBinding::inflate)
    private val vm by viewModel<MainViewModel>()

    private val repoInfoAdapter by lazy { RepoInfoAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vm.fetch()
        vb.rvRepoInfo.apply {
            adapter = repoInfoAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        lifecycleScope.launch {
            vm.repoInfoFlow.collect {
                repoInfoAdapter.submitList(it)
                Log.d("MainActivity", it.toString())
            }
        }
    }
}