package com.plcoding.daggerscopes

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Singleton

// there is no need to be specific about which scope has it, it can be made in the scope that provides it
class SessionTimer {

    private var isRunning = false
    var sessionTime = 0

    fun startTimer() {
        CoroutineScope(Dispatchers.Main).launch {
            while (isRunning) {
                delay(1000L)
                sessionTime++
            }
        }
    }

    fun stopTimer() {
        isRunning = false
        sessionTime = 0
    }
}