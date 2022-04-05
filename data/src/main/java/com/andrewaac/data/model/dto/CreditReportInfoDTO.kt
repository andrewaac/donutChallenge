package com.andrewaac.data.model.dto

import com.google.gson.annotations.SerializedName

data class CreditReportInfoDTO(
    @SerializedName("equifaxScoreBand") val equifaxScoreBand: Int,
    @SerializedName("equifaxScoreBandDescription") val equifaxScoreBandDescription: String,
    @SerializedName("maxScoreValue") val maxScoreValue: Int,
    @SerializedName("minScoreValue") val minScoreValue: Int,
    @SerializedName("score") val score: Int,
)
