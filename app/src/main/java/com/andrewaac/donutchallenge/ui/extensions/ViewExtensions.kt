package com.andrewaac.donutchallenge.ui.extensions

import android.view.View

fun View.changeVisibility(shouldBeVisible: Boolean, hiddenStrategy: Int = View.GONE) {
    val visibility = if (shouldBeVisible) {
        View.VISIBLE
    } else {
        hiddenStrategy
    }
    this.visibility = visibility
}
