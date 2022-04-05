package com.andrewaac.donutchallenge.ui.components.donutView

import android.animation.ObjectAnimator
import android.widget.ProgressBar

private const val PROGRESS_BAR_ANIMATION_DURATION = 500L
private const val PROGRESS_BAR_ANIMATION_START_VALUE = 0
private const val PROGRESS_BAR_PROPERTY_PROGRESS = "progress"

internal fun ProgressBar.update(score: Int) {
    ObjectAnimator
        .ofInt(this, PROGRESS_BAR_PROPERTY_PROGRESS, PROGRESS_BAR_ANIMATION_START_VALUE, score)
        .setDuration(PROGRESS_BAR_ANIMATION_DURATION)
        .start()
}
