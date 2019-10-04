package com.jesusbadenas.kotlin_clean_architecture_project.userlist

import android.os.Bundle
import butterknife.ButterKnife
import butterknife.OnClick
import com.jesusbadenas.kotlin_clean_architecture_project.R
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseActivity

/**
 * Main application screen. This is the app entry point.
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.btn_LoadData)
    fun navigateToUserList() {
        navigator?.navigateToUserList(this)
    }
}
