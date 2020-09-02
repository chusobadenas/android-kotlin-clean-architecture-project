package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseViewModel
import com.jesusbadenas.kotlin_clean_architecture_project.common.LiveEvent

class MainViewModel: BaseViewModel() {

    val loadAction = LiveEvent<Nothing>()

    fun onLoadButtonClick() {
        loadAction.call()
    }
}
