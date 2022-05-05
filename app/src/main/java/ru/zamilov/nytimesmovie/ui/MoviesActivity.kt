package ru.zamilov.nytimesmovie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import kotlinx.coroutines.flow.collectLatest
import ru.zamilov.nytimesmovie.App
import ru.zamilov.nytimesmovie.databinding.ActivityMoviesBinding

class MoviesActivity : AppCompatActivity() {

    private lateinit var viewModel: MoviesViewModel
    private lateinit var binding: ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as App).appContainer
        val adapter = MoviesAdapter()
        viewModel = ViewModelProvider(this,
            ViewModelFactory(appContainer.repository))[MoviesViewModel::class.java]

        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)

        binding.vacanciesList.adapter = adapter.withLoadStateFooter(
            MoviesLoadStateAdapter { adapter.retry() }
        )

        lifecycleScope.launchWhenCreated {
            viewModel.flow.collectLatest {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collectLatest { loadState ->
                val isListEmpty =
                    loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
                binding.emptyList.isVisible = isListEmpty
                binding.vacanciesList.isVisible = !isListEmpty
                binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

                binding.swipeRefresh.isRefreshing = loadState.mediator?.refresh is LoadState.Loading
            }
        }

        binding.swipeRefresh.setOnRefreshListener { adapter.refresh() }

        binding.retryButton.setOnClickListener { adapter.retry() }
    }
}