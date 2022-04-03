package com.andrewaac.donutchallenge.usecase

import com.andrewaac.donutchallenge.model.CreditReportInfo
import com.andrewaac.donutchallenge.model.CreditScore
import com.andrewaac.donutchallenge.repository.CreditScoreRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetCreditScoreUseCaseTest {

    private val emptyCreditScore = CreditScore.EmptyCreditScore

    private val creditReportInfo = CreditReportInfo(
        maxScoreValue = 100,
        minScoreValue = 0,
        score = 10
    )
    private val validCreditScore = CreditScore.ValidCreditScore(creditReportInfo)

    private val creditScoreRepository: CreditScoreRepository = mock()
    private val sut = GetCreditScoreUseCase(creditScoreRepository)

    @Nested
    @DisplayName("given getCreditScore is called")
    inner class GivenCreditScore {

        @Test
        fun `whenever repository returns empty credit score, then EmptyCreditScore is returned`() {
            runBlocking {
                val expected = emptyCreditScore

                whenever(creditScoreRepository.getCreditScore()).thenReturn(emptyCreditScore)

                val actual = sut.getCreditScore()

                assertEquals(expected, actual)
            }
        }

        @Test
        fun `whenever repository returns valid credit score, then ValidCreditScore is returned`() {
            runBlocking {
                val expected = validCreditScore

                whenever(creditScoreRepository.getCreditScore()).thenReturn(validCreditScore)

                val actual = sut.getCreditScore()

                assertEquals(expected, actual)
            }
        }
    }


}
