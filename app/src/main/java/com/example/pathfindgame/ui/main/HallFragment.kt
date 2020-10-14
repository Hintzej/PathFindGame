package com.example.pathfindgame.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.pathfindgame.R
import com.example.pathfindgame.databinding.FragmentHallBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HallFragment : Fragment() {

    private lateinit var binding: FragmentHallBinding
    private lateinit var navController: NavController

    private val hallViewModel: HallViewModel by lazy {
        ViewModelProvider(this).get(HallViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hall, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setUpBindings()
    }

    private fun setUpBindings() {
        binding.hallViewModel = hallViewModel

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            for (event in hallViewModel.channel) {
                when (event) {
                    is HallViewModel.Event.WonGame -> goToWinScreen()
                    is HallViewModel.Event.NextHall -> advanceToNextHall()
                    is HallViewModel.Event.WrongChoice -> startOver()
                }
            }
        }

        binding.lifecycleOwner = viewLifecycleOwner

        binding.hallway = hallViewModel.currentHallway
    }

    private fun goToWinScreen() {
        Toast.makeText(context, "You did it!", Toast.LENGTH_SHORT).show()
        navController.navigate(R.id.action_hallFragment_to_winScreenFragment)
    }

    private fun advanceToNextHall() {
        Toast.makeText(context, "Correct, but you reach another fork", Toast.LENGTH_SHORT).show()
        updateBinding()
    }

    private fun startOver() {
        Toast.makeText(context, "Incorrect, try again at the start", Toast.LENGTH_SHORT).show()
        updateBinding()
    }

    private fun updateBinding() {
        binding.hallway = hallViewModel.currentHallway
    }
}