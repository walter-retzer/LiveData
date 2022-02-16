package com.wdretzer.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel2 : ViewModel() {

    private lateinit var timer: CountDownTimer
    private val _seconds = MutableLiveData<Int>()
    var timerValue = MutableLiveData<Long>()

    var finished = MutableLiveData<Boolean>()

    fun seconds(): LiveData<Int>{
        return  _seconds
    }

    fun startTimer() {
        timer = object : CountDownTimer(timerValue.value!!.toLong(), 1000) {
            override fun onTick(p0: Long) {
                val timeLeft = p0/1000
                _seconds.value = timeLeft.toInt()
            }

            override fun onFinish() {
                finished.value = true
            }
        }.start()
    }

    fun stopTimmer(){
        timer.cancel()
    }


}