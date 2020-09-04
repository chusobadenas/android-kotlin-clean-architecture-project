package com.jesusbadenas.kotlin_clean_architecture_project.userlist

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jesusbadenas.kotlin_clean_architecture_project.R
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseActivity
import com.jesusbadenas.kotlin_clean_architecture_project.databinding.ActivityMainBinding
import com.jesusbadenas.kotlin_clean_architecture_project.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Main application screen. This is the app entry point.
 */
class MainActivity : BaseActivity() {

    private val mainVM: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = mainVM

        subscribe()
    }

    private fun subscribe() {
        mainVM.loadAction.observe(this) {
            navigateToUserList()
        }
    }

    private fun navigateToUserList() {
        navigator.navigateToUserList(this)
    }
}
