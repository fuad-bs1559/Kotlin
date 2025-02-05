/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.kotlin_demo.screens.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.kotlin_demo.R
import com.example.kotlin_demo.databinding.GameFragmentBinding
import timber.log.Timber

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel


    private lateinit var binding: GameFragmentBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )

        Timber.i("GameFragment", "Called ViewModelProviders.of!")
        viewModel = ViewModelProviders.of(this)[GameViewModel::class.java]

        binding.gameViewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        viewModel.eventGameFinish.observe(viewLifecycleOwner)  { hashFinished ->
            if(hashFinished == true){
                gameFinished()
                viewModel.onGameFinishComplete()
            }
        }

        updateScoreText()
        updateWordText()
        updateTimerText()
        return binding.root

    }

    /**
     * Resets the list of words and randomizes the order
     */

    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        val action = viewModel.score.value?.let { GameFragmentDirections.actionGameToScore(it) }
        if (action != null) {
            findNavController(this).navigate(action)
        }
    }

    /** Methods for updating the UI **/

    private fun updateWordText() {
        binding.wordText.text = viewModel.word.value
    }

    private fun updateTimerText(){
        binding.timerText.text = viewModel.currentTime.toString()
    }

    @SuppressLint("SetTextI18n")
    private fun updateScoreText() {
        binding.scoreText.text = viewModel.score.toString()
    }
}
