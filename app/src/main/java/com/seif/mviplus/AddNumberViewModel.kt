package com.seif.mviplus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception


class AddNumberViewModel : ViewModel() {
    // we need to open channel bet viewModel and activity to send those intents
    val intentChannel = Channel<MainIntent>(Channel.UNLIMITED)

    // encapsulation
    private val _viewState = MutableStateFlow<MainViewState>(MainViewState.Idle)
    val state: StateFlow<MainViewState> get() = _viewState
    private var number = 0

    init {
        processIntent()
    }
    // process intent
    private fun processIntent() { // we can call it from mainActivity if it's public
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.AddNumber -> addNumber()
                }
            }
        }

    }

    // reduce result came from process to send it to activity
    private fun addNumber() {
        viewModelScope.launch {
            _viewState.value =
                try {
                    MainViewState.Number(number++)
                } catch (e: Exception) {
                    MainViewState.Error(e.message!!)
                }
        }
    }
}