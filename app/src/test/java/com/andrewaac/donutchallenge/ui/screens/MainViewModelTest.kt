package com.andrewaac.donutchallenge.ui.screens

import com.andrewaac.donutchallenge.model.CreditReportInfo
import com.andrewaac.donutchallenge.model.CreditScore
import com.andrewaac.donutchallenge.repository.CreditScoreRepository
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
internal class MainViewModelTest {

    @JvmField
    @RegisterExtension
    val coroutineTestExtension = CoroutineTestExtension()

    private val testDispatcher = coroutineTestExtension.testDispatcher

    private val creditScoreRepository: CreditScoreRepository = mock()
    private val getCreditScoreUseCase = GetCreditScoreUseCase(creditScoreRepository)

    private lateinit var stateObserver: TestObserver<ViewState>
    private lateinit var sut: MainViewModel

    @BeforeEach
    fun setup() {
        sut = MainViewModel(getCreditScoreUseCase, testDispatcher)
        stateObserver = sut.state.test()
    }

    @Test
    fun `when onViewCreated is called, then Loading ViewState is emitted`() {
        runTest {
            val expected = ViewState.Loading

            sut.onViewCreated()

            stateObserver.assertValue(expected)
        }
    }

    @Test
    fun `given getCreditScoreUseCase returns EmptyCreditScore, when getCreditScore is called, them Error ViewState is emitted`() {
        runTest {
            val expected = ViewState.Error
            given(creditScoreRepository.getCreditScore()).willReturn(CreditScore.EmptyCreditScore)

            sut.getCreditScore()

            stateObserver.assertValue(expected)
        }
    }

    @Test
    fun `given getCreditScoreUseCase returns ValidCreditScore, when getCreditScore is called, them Loaded ViewState is emitted`() {
        runTest {
            val minScore = 0
            val maxScore = 100
            val score = 10
            val expected = ViewState.Loaded(maxScore, minScore, score)
            whenever(creditScoreRepository.getCreditScore()).thenReturn(
                CreditScore.ValidCreditScore(
                    CreditReportInfo(maxScore, minScore, score)
                )
            )

            sut.getCreditScore()

            stateObserver.assertValue(expected)
        }
    }
}
