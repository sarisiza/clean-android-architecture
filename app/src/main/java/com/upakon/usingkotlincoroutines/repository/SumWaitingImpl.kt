package com.upakon.usingkotlincoroutines.repository

import android.os.CountDownTimer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class SumWaitingImpl : SumWaiting {
    override suspend fun sumNumbers(a: Int, b: Int): Int = a + b
    override fun countDownTimer(seconds: Int): Flow<String> = flow {
        var countDown = seconds * 100L
        while (countDown >= 0) {
            val secLeft = countDown / 100
            val centiLeft = countDown % 100
            emit("${secLeft}:${centiLeft}")
            countDown -= 1
            delay(10)
        }
    }

}