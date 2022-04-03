package com.andrewaac.donutchallenge.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.andrewaac.donutchallenge.R
import com.andrewaac.donutchallenge.model.CreditScore
import com.andrewaac.donutchallenge.ui.components.DonutView
import com.andrewaac.donutchallenge.ui.components.State
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
        viewModel.state.observe(this) {
            when (it) {
                ViewState.Loading -> donutView.updateState(State.Loading)
                ViewState.Error -> donutView.updateState(State.Error)
                is ViewState.Loaded -> {
                    val donutState = State.Loaded(
                        score = it.score,
                        maxScore = it.maxScore,
                        minScore = it.minScore
                    )
                    donutView.updateState(donutState)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        donutView.updateState(State.Loading)
        button.setOnClickListener {
            viewModel.onViewCreated()
        }
    }
}
