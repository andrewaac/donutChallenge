package com.andrewaac.donutchallenge.ui.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.andrewaac.donutchallenge.R
import com.andrewaac.donutchallenge.ui.components.donutView.DonutView
import com.andrewaac.donutchallenge.ui.components.donutView.DonutViewClickListener
import com.andrewaac.donutchallenge.ui.components.donutView.DonutViewState
import com.andrewaac.donutchallenge.ui.components.donutView.DonutViewState.Error
import com.andrewaac.donutchallenge.ui.components.donutView.DonutViewState.Loaded
import com.andrewaac.donutchallenge.ui.components.donutView.DonutViewState.Loading
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), DonutViewClickListener {

    private lateinit var donutView: DonutView

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        donutView = findViewById(R.id.donut)
        donutView.donutViewClickListener = this
        setupViewModelObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCreditScore()
    }

    override fun onDonutViewClicked(donutViewState: DonutViewState) {
        when (donutViewState) {
            Error -> viewModel.getCreditScore()
            Loading -> Unit
            is Loaded -> Toast.makeText(this, "Do something", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupViewModelObservers() {
        viewModel.state.observe(this) {
            when (it) {
                ViewState.Loading -> donutView.updateState(Loading)
                ViewState.Error -> donutView.updateState(Error)
                is ViewState.Loaded -> {
                    val donutState = Loaded(
                        score = it.score,
                        maxScore = it.maxScore,
                        minScore = it.minScore
                    )
                    donutView.updateState(donutState)
                }
            }
        }
    }
}
