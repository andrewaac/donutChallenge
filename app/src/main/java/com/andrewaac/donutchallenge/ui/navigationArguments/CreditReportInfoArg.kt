package com.andrewaac.donutchallenge.ui.navigationArguments

import android.os.Parcelable
import com.andrewaac.donutchallenge.model.CreditReportInfo
import com.andrewaac.donutchallenge.ui.screens.home.HomeViewState
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreditReportInfoArg(
    val equifaxScoreBand: Int,
    val equifaxScoreBandDescription: String,
    val maxScoreValue: Int,
    val minScoreValue: Int,
    val score: Int,
) : Parcelable {

    fun toDomain() =
        CreditReportInfo(
            equifaxScoreBand = this.equifaxScoreBand,
            equifaxScoreBandDescription = this.equifaxScoreBandDescription,
            maxScoreValue = this.maxScoreValue,
            minScoreValue = this.minScoreValue,
            score = this.score,
        )
}

fun HomeViewState.Loaded.toCreditReportInfoArg() =
    CreditReportInfoArg(
        equifaxScoreBand = this.equifaxScoreBand,
        equifaxScoreBandDescription = this.equifaxScoreBandDescription,
        maxScoreValue = this.maxScore,
        minScoreValue = this.maxScore,
        score = this.score,
    )
