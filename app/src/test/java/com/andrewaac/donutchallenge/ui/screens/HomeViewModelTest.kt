package com.andrewaac.donutchallenge.ui.screens

import com.andrewaac.donutchallenge.model.CreditReportInfo
import com.andrewaac.donutchallenge.model.CreditScore
import com.andrewaac.donutchallenge.repository.CreditScoreRepository
import com.andrewaac.donutchallenge.ui.screens.home.HomeViewState
import com.andrewaac.donutchallenge.ui.screens.utils.CoroutineTestExtension
import com.andrewaac.donutchallenge.ui.screens.utils.InstantExecutorExtension
import com.andrewaac.donutchallenge.ui.screens.utils.TestObserver
import com.andrewaac.donutchallenge.ui.screens.utils.test
import com.andrewaac.donutchallenge.usecase.GetCreditScoreUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
internal class HomeViewModelTest {

    @JvmField
    @RegisterExtension
    val coroutineTestExtension = CoroutineTestExtension()

    private val testDispatcher = coroutineTestExtension.testDispatcher

    private val creditScoreRepository: CreditScoreRepository = mock()
    private val getCreditScoreUseCase = GetCreditScoreUseCase(creditScoreRepository)

    private lateinit var stateObserverHome: TestObserver<HomeViewState>
    private lateinit var sut: HomeViewModel

    @BeforeEach
    fun setup() {
        sut = HomeViewModel(getCreditScoreUseCase, testDispatcher)
        stateObserverHome = sut.stateHome.test()
    }

    @Test
    fun `given usecase returns EmptyCreditScore, when getCreditScore is called, then Error ViewState is emitted`() {
        runTest {
            val expected = arrayOf(HomeViewState.Loading, HomeViewState.Error)
            given(creditScoreRepository.getCreditScore()).willReturn(CreditScore.EmptyCreditScore)

            sut.getCreditScore()

            stateObserverHome.assertValues(expected)
        }
    }

    @Test
    fun `given usecase returns ValidCreditScore, when getCreditScore is called, then Loaded ViewState is emitted`() {
        runTest {
            val minScore = 0
            val maxScore = 100
            val score = 10
            val expected = arrayOf(HomeViewState.Loading, HomeViewState.Loaded(maxScore, minScore, score))
            whenever(creditScoreRepository.getCreditScore()).thenReturn(
                CreditScore.ValidCreditScore(
                    CreditReportInfo(maxScore, minScore, score)
                )
            )

            sut.getCreditScore()

            stateObserverHome.assertValues(expected)
        }
    }
}
