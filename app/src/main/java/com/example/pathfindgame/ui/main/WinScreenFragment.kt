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
import com.example.pathfindgame.databinding.WinScreenFragmentBinding

class WinScreenFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: WinScreenFragmentBinding

    private val viewModel: WinScreenViewModel by lazy {
        ViewModelProvider(this).get(WinScreenViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.win_screen_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setUpBindings()
    }

    private fun setUpBindings() {
        binding.winScreenViewModel = viewModel

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            for (event in viewModel.channel) {
                when (event) {
                    is WinScreenViewModel.Event.OnHomeButtonPress -> returnToHomeScreen()
                }
            }
        }
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun returnToHomeScreen() {
        navController.popBackStack()
    }
}