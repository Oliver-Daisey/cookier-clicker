package com.example.moralfirstapp

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CookieViewModel : ViewModel() {
    private val _cookieCount = MutableStateFlow(0)
    val cookieCount: StateFlow<Int> = _cookieCount.asStateFlow()

    private val _countdownActive = MutableStateFlow(false)
    val countdownActive: StateFlow<Boolean> = _countdownActive.asStateFlow()

    private val _isButtonClickable = MutableStateFlow(true)
    val isButtonClickable: StateFlow<Boolean> = _isButtonClickable.asStateFlow()

    private val _clock = MutableStateFlow(0)
    val clock: StateFlow<Int> = _clock.asStateFlow()

    fun incrementCookieCount() {
        _cookieCount.value = _cookieCount.value.plus(1)
    }

    private fun startNewGame() {

        val countDownInterval = 1000L // interval between ui updates
        val countDownLength = 10000L // how long to play for
        val coolDownLength = 3000L // how long to show score for

        _cookieCount.value = 1 // Reset cookie count
        _countdownActive.value = true
        _clock.value = countDownLength.toInt() / 1000 + 1

        object: CountDownTimer(countDownLength, countDownInterval) { // 1 minute countdown
            override fun onTick(millisUntilFinished: Long) {
                _clock.value = _clock.value.minus(1)
            }

            override fun onFinish() {
                // Update UI components after countdown finishes if needed
                _isButtonClickable.value = false // Set button clickable state to false
                _countdownActive.value = false // Set countdown state to inactive
                object: CountDownTimer(coolDownLength, countDownInterval) { // 3 second cooldown

                    override fun onTick(millisUntilFinished: Long) {
                    }

                    override fun onFinish() {
                        // Update UI components after cooldown finishes if needed
                        _isButtonClickable.value = true // Set button clickable state to true
                    }
                }.start()
            }

        }.start()



    }

    fun onCookieClicked() {

        if (_countdownActive.value) { // If countdown is active
            incrementCookieCount() // Increment cookie count
        } else { // If countdown is inactive
            startNewGame() // Start a new game
        }

    }

}