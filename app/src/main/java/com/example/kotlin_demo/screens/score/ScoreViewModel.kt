package com.example.kotlin_demo.screens.score

import androidx.lifecycle.ViewModel
import timber.log.Timber

class ScoreViewModel(finalScore: Int) : ViewModel() {
    init {
        Timber.i("ScoreViewModel", "Final Score is $finalScore")
    }
}