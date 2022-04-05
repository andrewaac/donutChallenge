package com.andrewaac.donutchallenge.model

data class CreditReportInfo(
    val equifaxScoreBand: Int,
    val equifaxScoreBandDescription: String,
    val maxScoreValue: Int,
    val minScoreValue: Int,
    val score: Int,
)
