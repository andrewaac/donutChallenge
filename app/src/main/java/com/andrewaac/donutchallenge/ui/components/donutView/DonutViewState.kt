package com.andrewaac.donutchallenge.ui.components.donutView

sealed class DonutViewState {
    object Loading : DonutViewState()
    data class Loaded(val score: Int, val maxScore: Int, val minScore: Int) : DonutViewState()
    object Error : DonutViewState()
}
