package com.jaehyeon.arlammanagertest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

/**
 * Created by Jaehyeon on 2022/08/13.
 */
class MainViewModel: ViewModel() {

    private val _state = MutableLiveData<ButtonState>()
    val state: LiveData<ButtonState> get() = _state

    fun action30S() {
        val triggerTime = Calendar.getInstance()
        _state.value = ButtonState.Action30S(triggerTime)
    }

    fun action1M() {
        val triggerTime = Calendar.getInstance()
        _state.value = ButtonState.Action1M(triggerTime)
    }

    fun action1H() {
        val triggerTime = Calendar.getInstance()
        _state.value = ButtonState.Action1H(triggerTime)
    }

    fun actionCancel() {
        _state.value = ButtonState.ActionCancel
    }

}