package com.jesusbadenas.kotlin_clean_architecture_project.userlist

import android.os.Bundle
import com.jesusbadenas.kotlin_clean_architecture_project.R
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main application screen. This is the app entry point.
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_LoadData.setOnClickListener {
            navigateToUserList()
        }
    }

    private fun navigateToUserList() {
        navigator.navigateToUserList(this)
    }
}
