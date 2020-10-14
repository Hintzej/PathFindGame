package com.example.pathfindgame.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.pathfindgame.R
import com.example.pathfindgame.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var navController: NavController

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        setUpBindings()
    }

    private fun setUpBindings() {
        binding.viewModel = mainViewModel

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            for (event in mainViewModel.channel) {
                when (event) {
                    is MainViewModel.Event.NavigateToHallways  -> navigateToHallways()
                }
            }
        }

        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun navigateToHallways() {
        navController.navigate(R.id.action_mainFragment_to_hallFragment)
    }
}