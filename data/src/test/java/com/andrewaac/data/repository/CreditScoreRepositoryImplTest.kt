package com.andrewaac.data.repository

import com.andrewaac.data.api.CreditScoreApi
import com.andrewaac.data.model.dto.CreditReportInfoDTO
import com.andrewaac.data.model.dto.CreditScoreDTO
import com.andrewaac.donutchallenge.model.CreditReportInfo
import com.andrewaac.donutchallenge.model.CreditScore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import retrofit2.Response

@ExperimentalCoroutinesApi
internal class CreditScoreRepositoryImplTest {

    private val creditScoreApi: CreditScoreApi = mock()

    @Test
    fun `given creditScoreApi return success, when getCreditScore is called, then ValidCreditScore is returned`() {
        runBlocking {
            val creditReportInfo =
                CreditReportInfo(
                    equifaxScoreBand = 4,
                    equifaxScoreBandDescription = "Great",
                    maxScoreValue = 100,
                    minScoreValue = 0,
                    score = 10,
                )
            val expected = CreditScore.ValidCreditScore(creditReportInfo)

            val sut = CreditScoreRepositoryImpl(creditScoreApi)
            given(creditScoreApi.getCreditScore()).willReturn(
                Response.success(
                    CreditScoreDTO(
                        CreditReportInfoDTO(
                            equifaxScoreBand = 4,
                            equifaxScoreBandDescription = "Great",
                            maxScoreValue = 100,
                            minScoreValue = 0,
                            score = 10,
                        )
                    )
                )
            )

            val actual = sut.getCreditScore()

            assertTrue(actual is CreditScore.ValidCreditScore)
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `given creditScoreApi return failure, when getCreditScore is called, then EmptyCreditScore is returned`() {
        runBlocking {
            val expected = CreditScore.EmptyCreditScore

            val sut = CreditScoreRepositoryImpl(creditScoreApi)
            given(creditScoreApi.getCreditScore()).willReturn(
                Response.error(400, "".toResponseBody(null))
            )

            val actual = sut.getCreditScore()

            assertTrue(actual is CreditScore.EmptyCreditScore)
            assertEquals(expected, actual)
        }
    }
}
