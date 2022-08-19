package com.oolong.screentimer20.presentation.countdown_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountdownScreenViewModel @Inject constructor(

): ViewModel() {

    private var _uiState = mutableStateOf(CountdownScreenState())
    val uiState: State<CountdownScreenState> = _uiState
}