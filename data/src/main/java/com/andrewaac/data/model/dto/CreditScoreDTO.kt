package com.andrewaac.data.model.dto

import com.andrewaac.donutchallenge.model.CreditReportInfo
import com.andrewaac.donutchallenge.model.CreditScore
import com.google.gson.annotations.SerializedName

data class CreditScoreDTO(
    @SerializedName("creditReportInfo") val creditReportInfoDTO: CreditReportInfoDTO
)

fun CreditScoreDTO?.toDomain(): CreditScore {
    return if (this == null) {
        CreditScore.EmptyCreditScore
    } else {
        CreditScore.ValidCreditScore(
            CreditReportInfo(
                equifaxScoreBand = this.creditReportInfoDTO.equifaxScoreBand,
                equifaxScoreBandDescription = this.creditReportInfoDTO.equifaxScoreBandDescription,
                maxScoreValue = this.creditReportInfoDTO.maxScoreValue,
                minScoreValue = this.creditReportInfoDTO.minScoreValue,
                score = this.creditReportInfoDTO.score,
            )
        )
    }
}
