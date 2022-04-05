package com.andrewaac.donutchallenge.ui.components

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.andrewaac.donutchallenge.R
import com.andrewaac.donutchallenge.ui.extensions.changeVisibility

private const val EMPTY_STRING = ""
private const val MAX_SCORE = 100
private const val PROGRESS_BAR_ANIMATION_DURATION = 500L
private const val PROGRESS_BAR_ANIMATION_START_VALUE = 0
private const val PROGRESS_BAR_PROPERTY_PROGRESS = "progress"

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

    fun updateState(donutState: DonutState) {
        when (donutState) {
            DonutState.Error -> showError()
            DonutState.Loading -> showingLoading()
            is DonutState.Loaded -> showLoaded(donutState)
        }
    }

    private fun showingLoading() {
        updateProgressDrawable()
        setProgressBarToLoading(true)
        updateTitleText()
        updateValueText(R.string.loading)
        updateMaxValue()
    }

    private fun showError() {
        updateProgressDrawable(R.drawable.progress_color_error)
        setProgressBarToLoading(false)
        updateTitleText()
        updateValueText(R.string.error)
        updateProgressBar(MAX_SCORE, MAX_SCORE)
        updateMaxValue()
    }

    private fun showLoaded(donutState: DonutState.Loaded) {
        try {
            updateProgressDrawable(R.drawable.progress_color)
            setProgressBarToLoading(false)
            updateTitleText(R.string.donut_title)
            val score = validateCurrentScore(donutState)
            updateValue(score)
            updateProgressBar(score, donutState.maxScore)
            updateMaxValue(donutState.maxScore)
        } catch (e: IllegalArgumentException) {
            showError()
        }
    }

    private fun setProgressBarToLoading(isLoading: Boolean) {
        donutProgressBar.isIndeterminate = isLoading
    }

    private fun updateTitleText(@StringRes titleText: Int? = null) {
        donutTitle.text = titleText?.let { context.getString(it) } ?: EMPTY_STRING
        donutTitle.changeVisibility(titleText != null)
    }

    private fun updateValueText(@StringRes valueText: Int? = null) {
        donutValue.text = valueText?.let { context.getString(it) } ?: EMPTY_STRING
        donutValue.changeVisibility(valueText != null)
    }

    private fun updateValue(valueText: Int) {
        donutValue.text = "$valueText"
        donutValue.changeVisibility(true)
    }

    private fun updateMaxValue(maxScore: Int? = null) {
        donutMaxValue.text =
            maxScore?.let { context.getString(R.string.score_out_of, maxScore) } ?: EMPTY_STRING
        donutMaxValue.changeVisibility(maxScore != null)
    }

    private fun updateProgressDrawable(@DrawableRes drawableRes: Int = R.drawable.progress_color) {
        val drawable = ContextCompat.getDrawable(context, drawableRes)
        donutProgressBar.progressDrawable = drawable
    }

    private fun validateCurrentScore(donutState: DonutState.Loaded): Int {
        return with(donutState) { score.coerceIn(minScore, maxScore) }
    }

    private fun updateProgressBar(currentScore: Int, maxScore: Int) {
        with(donutProgressBar) {
            max = maxScore
            update(currentScore)
        }
    }

    private fun ProgressBar.update(score: Int) {
        ObjectAnimator
            .ofInt(this, PROGRESS_BAR_PROPERTY_PROGRESS, PROGRESS_BAR_ANIMATION_START_VALUE, score)
            .setDuration(PROGRESS_BAR_ANIMATION_DURATION)
            .start()
    }

    sealed class DonutState {
        object Loading : DonutState()
        data class Loaded(val score: Int, val maxScore: Int, val minScore: Int) : DonutState()
        object Error : DonutState()
    }
}
