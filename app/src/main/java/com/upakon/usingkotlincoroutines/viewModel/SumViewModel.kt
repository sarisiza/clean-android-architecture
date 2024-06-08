package com.upakon.usingkotlincoroutines.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upakon.usingkotlincoroutines.repository.SumWaiting
import com.upakon.usingkotlincoroutines.repository.SumWaitingImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SumViewModel : ViewModel() {

    private val sumWaiting : SumWaiting = SumWaitingImpl()

    private val _timerState : MutableStateFlow<String> = MutableStateFlow("")
    val timerState = _timerState.asStateFlow()

    private val _sum : MutableStateFlow<Int> = MutableStateFlow(0)
    val sum = _sum.asStateFlow()

    fun sumNumbers(a: Int, b: Int, seconds : Int){
        viewModelScope.launch(Dispatchers.IO) {
            sumWaiting.countDownTimer(seconds).collect{
                _timerState.value = it
            }
            _sum.value = sumWaiting.sumNumbers(a,b)
        }
    }

}