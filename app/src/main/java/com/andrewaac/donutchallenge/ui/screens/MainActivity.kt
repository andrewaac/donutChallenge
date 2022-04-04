package com.andrewaac.donutchallenge.ui.screens

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.andrewaac.donutchallenge.R
import com.andrewaac.donutchallenge.ui.components.DonutView
import com.andrewaac.donutchallenge.ui.components.DonutView.State.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var donutView: DonutView
    private lateinit var button: Button

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        donutView = findViewById(R.id.donut)
        button = findViewById(R.id.button)
        setupViewModelObservers()
        viewModel.onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCreditScore()
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
