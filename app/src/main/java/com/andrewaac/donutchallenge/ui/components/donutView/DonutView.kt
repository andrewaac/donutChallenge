package com.andrewaac.donutchallenge.ui.components.donutView

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.andrewaac.donutchallenge.R
import com.andrewaac.donutchallenge.databinding.LayoutDonutBinding
import com.andrewaac.donutchallenge.ui.extensions.changeVisibility

class DonutView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private var binding: LayoutDonutBinding =
        LayoutDonutBinding.inflate(LayoutInflater.from(context), this, true)

    private var viewState: DonutViewState = DonutViewState.Loading

    var donutViewClickListener: DonutViewClickListener? = null

    init {
        this.setOnClickListener {
            donutViewClickListener?.onDonutViewClicked(viewState)
        }
    }

    fun updateState(donutViewState: DonutViewState) {
        viewState = donutViewState
        when (donutViewState) {
            DonutViewState.Error -> showError()
            DonutViewState.Loading -> showingLoading()
            is DonutViewState.Loaded -> showLoaded(donutViewState)
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

    private fun showLoaded(donutViewState: DonutViewState.Loaded) {
        try {
            updateProgressDrawable(R.drawable.progress_color)
            setProgressBarToLoading(false)
            updateTitleText(R.string.donut_title)
            val score = validateCurrentScore(donutViewState)
            updateValue(score)
            updateProgressBar(score, donutViewState.maxScore)
            updateMaxValue(donutViewState.maxScore)
        } catch (e: IllegalArgumentException) {
            Log.e(javaClass.simpleName, null, e)
            showError()
        }
    }

    private fun setProgressBarToLoading(isLoading: Boolean) {
        binding.donutProgress.isIndeterminate = isLoading
    }

    private fun updateTitleText(@StringRes titleText: Int? = null) {
        with(binding.donutTitle) {
            text = titleText?.let { context.getString(it) } ?: EMPTY_STRING
            changeVisibility(titleText != null)
        }
    }

    private fun updateValueText(@StringRes valueText: Int? = null) {
        with(binding.donutValue) {
            text = valueText?.let { context.getString(it) } ?: EMPTY_STRING
            changeVisibility(valueText != null)
        }
    }

    private fun updateValue(valueText: Int) {
        with(binding.donutValue) {
            text = "$valueText"
            changeVisibility(true)
        }
    }

    private fun updateMaxValue(maxScore: Int? = null) {
        with(binding.donutMaxValue) {
            text =
                maxScore?.let { context.getString(R.string.score_out_of, maxScore) } ?: EMPTY_STRING
            changeVisibility(maxScore != null)
        }
    }

    private fun updateProgressDrawable(@DrawableRes drawableRes: Int = R.drawable.progress_color) {
        val drawable = ContextCompat.getDrawable(context, drawableRes)
        binding.donutProgress.progressDrawable = drawable
    }

    private fun validateCurrentScore(donutViewState: DonutViewState.Loaded): Int {
        return with(donutViewState) { score.coerceIn(minScore, maxScore) }
    }

    private fun updateProgressBar(currentScore: Int, maxScore: Int) {
        with(binding.donutProgress) {
            max = maxScore
            update(currentScore)
        }
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val MAX_SCORE = 100
    }
}
