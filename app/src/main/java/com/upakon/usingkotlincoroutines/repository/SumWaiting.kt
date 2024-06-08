package com.upakon.usingkotlincoroutines.repository

import kotlinx.coroutines.flow.Flow

interface SumWaiting {

    suspend fun sumNumbers (a: Int, b: Int) : Int

    fun countDownTimer (seconds: Int) : Flow<String>

}