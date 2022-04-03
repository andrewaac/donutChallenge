package com.andrewaac.donutchallenge.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.andrewaac.donutchallenge.R
import com.andrewaac.donutchallenge.ui.components.DonutView

class MainActivity : AppCompatActivity() {

    private lateinit var donutView: DonutView
    private lateinit var button: Button

    private var currentScore = 0
    private var maxScore = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        donutView = findViewById(R.id.donut)
        button = findViewById(R.id.button)
    }

    override fun onResume() {
        super.onResume()
        donutView.updateState(DonutView.State.Loading)
        button.setOnClickListener {
            currentScore += 10
            if (currentScore > 100) {
                donutView.updateState(DonutView.State.Loading)
                currentScore = 0
            } else {
                donutView.updateState(DonutView.State.Loaded(currentScore, maxScore))
            }
        }
    }
}
