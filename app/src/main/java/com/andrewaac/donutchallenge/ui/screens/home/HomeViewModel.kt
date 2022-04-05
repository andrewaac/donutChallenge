package com.andrewaac.donutchallenge.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrewaac.donutchallenge.model.CreditScore
import com.andrewaac.donutchallenge.usecase.GetCreditScoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCreditScoreUseCase: GetCreditScoreUseCase,
    @Named("io") private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    val stateHome: LiveData<HomeViewState>
        get() = _state
    private val _state = MutableLiveData<HomeViewState>()

    fun getCreditScore() {
        viewModelScope.launch {
            _state.value = HomeViewState.Loading
            val creditScore = withContext(ioDispatcher) {
                getCreditScoreUseCase.getCreditScore()
            }
            val newState = when (creditScore) {
                CreditScore.EmptyCreditScore -> HomeViewState.Error
                is CreditScore.ValidCreditScore -> HomeViewState.Loaded(
                    score = creditScore.creditReportInfo.score,
                    maxScore = creditScore.creditReportInfo.maxScoreValue,
                    minScore = creditScore.creditReportInfo.minScoreValue
                )
            }
            _state.value = newState
        }
    }
}
