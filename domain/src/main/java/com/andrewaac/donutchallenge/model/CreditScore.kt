package com.andrewaac.donutchallenge.model

sealed class CreditScore {
    object EmptyCreditScore : CreditScore()
    data class ValidCreditScore(val creditReportInfo: CreditReportInfo) : CreditScore()
}
