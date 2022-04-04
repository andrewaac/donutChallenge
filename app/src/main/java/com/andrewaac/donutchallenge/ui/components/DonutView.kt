package com.andrewaac.donutchallenge.ui.components

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.andrewaac.donutchallenge.R
import com.andrewaac.donutchallenge.ui.extensions.changeVisibility
import java.lang.IllegalArgumentException

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
            State.Error -> showError()
            State.Loading -> showingLoading()
            is State.Loaded -> showLoaded(state)
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

    private fun showLoaded(state: State.Loaded) {
        try {
            val score = validateCurrentScore(state)
            updateScore(score, state.maxScore)
        } catch (e: IllegalArgumentException) {
            showError()
        }
    }

    private fun updateScore(score: Int, maxScore: Int) {
        donutTitle.text = context.getString(R.string.donut_title)
        donutProgressBar.isIndeterminate = false

        updateProgressBar(score, maxScore)
        updateCurrentScore(score)
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


    private fun validateCurrentScore(state: State.Loaded): Int {
        return with(state) { score.coerceIn(minScore, maxScore) }
    }

    private fun updateProgressBar(currentScore: Int, maxScore: Int) {
        with(donutProgressBar) {
            max = maxScore
            update(currentScore)
        }
    }

    private fun updateCurrentScore(currentScore: Int) {
        donutValue.text = "$currentScore"
    }

    private fun updateMaxScore(score: Int) {
        donutMaxValue.text = context.getString(R.string.score_out_of, score)
    }

    private fun ProgressBar.update(score: Int) {
        ObjectAnimator
            .ofInt(this, "progress", 0, score)
            .setDuration(500)
            .start()
    }

    sealed class State {
        object Loading : State()
        data class Loaded(val score: Int, val maxScore: Int, val minScore: Int) : State()
        object Error : State()
    }
}
