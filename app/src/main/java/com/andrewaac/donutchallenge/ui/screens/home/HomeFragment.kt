package com.andrewaac.donutchallenge.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andrewaac.donutchallenge.databinding.FragmentHomeBinding
import com.andrewaac.donutchallenge.ui.components.donutView.DonutViewClickListener
import com.andrewaac.donutchallenge.ui.components.donutView.DonutViewState
import com.andrewaac.donutchallenge.ui.components.donutView.DonutViewState.Error
import com.andrewaac.donutchallenge.ui.components.donutView.DonutViewState.Loaded
import com.andrewaac.donutchallenge.ui.components.donutView.DonutViewState.Loading
import com.andrewaac.donutchallenge.ui.navigationArguments.toCreditReportInfoArg
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), DonutViewClickListener {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.donut.donutViewClickListener = this@HomeFragment
        setupViewModelObservers()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCreditScore()
    }

    override fun onDonutViewClicked(donutViewState: DonutViewState) {
        when (donutViewState) {
            Error -> viewModel.getCreditScore()
            Loading -> Unit
            is Loaded -> showDetails()
        }
    }

    private fun showDetails() {
        val currentState = viewModel.stateHome.value
        if (currentState is HomeViewState.Loaded) {
            findNavController()
                .navigate(
                    HomeFragmentDirections.goToCreditDetailsFragment(
                        currentState.toCreditReportInfoArg()
                    )
                )
        }
    }

    private fun setupViewModelObservers() {
        viewModel.stateHome.observe(viewLifecycleOwner) {
            val state = when (it) {
                HomeViewState.Loading -> Loading
                HomeViewState.Error -> Error
                is HomeViewState.Loaded -> Loaded(
                    score = it.score,
                    maxScore = it.maxScore,
                    minScore = it.minScore
                )
            }
            binding.donut.updateState(state)
        }
    }
}
