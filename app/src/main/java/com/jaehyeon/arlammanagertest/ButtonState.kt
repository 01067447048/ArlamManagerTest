package com.jaehyeon.arlammanagertest

import java.util.*

sealed class ButtonState {

    data class Action30S(val time: Calendar): ButtonState()
    data class Action1M(val time: Calendar): ButtonState()
    data class Action1H(val time: Calendar): ButtonState()
    object ActionCancel: ButtonState()

}
