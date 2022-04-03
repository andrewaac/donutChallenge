package com.andrewaac.donutchallenge.ui.components

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.andrewaac.donutchallenge.R
import com.andrewaac.donutchallenge.ui.extensions.changeVisibility

class DonutView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private var donutProgressBar: ProgressBar
    private var donutTitle: TextView
    private var donutValue: TextView
    private var donutMaxValue: TextView

    init {
        inflate(context, R.layout.layout_donut, this)
        donutProgressBar = findViewById(R.id.donut_progress)
        donutTitle = findViewById(R.id.donut_title)
        donutValue = findViewById(R.id.donut_value)
        donutMaxValue = findViewById(R.id.donut_max_value)
    }

    fun updateState(state: State) {
        when (state) {
            State.Loading -> showingLoading()
            is State.Loaded -> updateScore(state.score, state.maxScore)
            State.Error -> showError()
        }
    }

    private fun showingLoading() {
        donutProgressBar.isIndeterminate = true
        donutValue.text = context.getString(R.string.loading)
        updateVisibility(isLoading = true)
    }

    private fun showError() {
        donutValue.text = context.getString(R.string.error)
        updateVisibility(isError = true)
    }

    private fun updateScore(score: Int, maxScore: Int) {
        donutTitle.text = context.getString(R.string.donut_title)
        donutProgressBar.isIndeterminate = false

        val currentScore = validateCurrentScore(score, maxScore)
        updateProgressBar(currentScore, maxScore)
        updateCurrentScore(currentScore)
        updateMaxScore(maxScore)

        updateVisibility()
    }

    private fun updateVisibility(isLoading: Boolean = false, isError: Boolean = false) {
        val shouldShowDonutProgressBar = !isError
        val shouldShowDonutTitle = !(isError || isLoading)
        val shouldShowDonutValue = true
        val shouldShowDonutMaxValue = !(isError || isLoading)

        donutProgressBar.changeVisibility(shouldShowDonutProgressBar)
        donutTitle.changeVisibility(shouldShowDonutTitle)
        donutValue.changeVisibility(shouldShowDonutValue)
        donutMaxValue.changeVisibility(shouldShowDonutMaxValue)
    }


    private fun validateCurrentScore(currentScore: Int, maxScore: Int): Int {
        return currentScore.coerceIn(0, maxScore)
    }

    private fun updateProgressBar(currentScore: Int, maxScore: Int) {
        donutProgressBar.max = maxScore
        donutProgressBar.progress = currentScore
    }

    private fun updateCurrentScore(currentScore: Int) {
        donutValue.text = "$currentScore"
    }

    private fun updateMaxScore(score: Int) {
        donutMaxValue.text = context.getString(R.string.score_out_of, score)
    }

    sealed class State {
        object Loading : State()
        data class Loaded(val score: Int, val maxScore: Int) : State()
        object Error : State()
    }
}
