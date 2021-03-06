package com.andrewaac.donutchallenge.ui.screens.home

sealed class HomeViewState {
    object Loading : HomeViewState()
    data class Loaded(
        val equifaxScoreBand: Int,
        val equifaxScoreBandDescription: String,
        val maxScore: Int,
        val minScore: Int,
        val score: Int,
    ) : HomeViewState()

    object Error : HomeViewState()
}
