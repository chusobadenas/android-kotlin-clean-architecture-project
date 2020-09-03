package com.jesusbadenas.kotlin_clean_architecture_project.common

import android.content.DialogInterface

data class UIError(
    val throwable: Throwable,
    val logMessage: String,
    var errorMsgId: Int? = null,
    var action: DialogInterface.OnClickListener? = null
)
