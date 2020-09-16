package com.jesusbadenas.kotlin_clean_architecture_project.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jesusbadenas.kotlin_clean_architecture_project.R
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseFragment
import com.jesusbadenas.kotlin_clean_architecture_project.databinding.FragmentMainBinding
import com.jesusbadenas.kotlin_clean_architecture_project.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment() {

    private val mainVM: MainViewModel by viewModel()

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Data binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this

        // View model
        binding.viewModel = mainVM
        subscribe()

        return binding.root
    }

    private fun subscribe() {
        mainVM.loadAction.observe(viewLifecycleOwner) {
            navigateToUserList()
        }
    }

    private fun navigateToUserList() {
        navigator.navigateToUserList(this)
    }
}
