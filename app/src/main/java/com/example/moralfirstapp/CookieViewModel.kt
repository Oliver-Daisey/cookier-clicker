package com.example.moralfirstapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CookieViewModel : ViewModel() {
    private val _cookieCount = MutableStateFlow(0)
    val cookieCount: StateFlow<Int> = _cookieCount.asStateFlow()

    fun incrementCookieCount() {
        _cookieCount.value = _cookieCount.value.plus(1)
    }

}