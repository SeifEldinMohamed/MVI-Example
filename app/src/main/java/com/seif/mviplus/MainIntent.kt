package com.seif.mviplus

sealed class MainIntent {
    object AddNumber : MainIntent() // we make it object as we will not have any parameters
}

// when we have parameters that we will take from mainActivity
// dataclass AddNumber(val number:Int): MainIntent()
