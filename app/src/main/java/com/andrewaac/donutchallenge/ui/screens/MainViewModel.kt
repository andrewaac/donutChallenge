package com.andrewaac.donutchallenge.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrewaac.donutchallenge.model.CreditScore
import com.andrewaac.donutchallenge.usecase.GetCreditScoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

sealed class ViewState {
    object Loading : ViewState()
    data class Loaded(val score: Int, val maxScore: Int, val minScore: Int) : ViewState()
    object Error : ViewState()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCreditScoreUseCase: GetCreditScoreUseCase
) : ViewModel() {

    val state: LiveData<ViewState>
        get() = _state
    private val _state = MutableLiveData<ViewState>()

    fun onViewCreated() {
        _state.value = ViewState.Loading
        getCreditScore()
    }

    private fun getCreditScore() {
        viewModelScope.launch {
            val creditScore = withContext(Dispatchers.IO) {
                getCreditScoreUseCase.getCreditScore()
            }
            val newState = when (creditScore) {
                CreditScore.EmptyCreditScore -> ViewState.Error
                is CreditScore.ValidCreditScore -> ViewState.Loaded(
                    score = creditScore.creditReportInfo.score,
                    maxScore = creditScore.creditReportInfo.maxScoreValue,
                    minScore = creditScore.creditReportInfo.minScoreValue
                )
            }
            _state.value = newState
        }
    }

}
