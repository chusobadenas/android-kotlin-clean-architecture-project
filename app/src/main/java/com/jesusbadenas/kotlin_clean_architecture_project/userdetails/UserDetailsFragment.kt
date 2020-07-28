package com.jesusbadenas.kotlin_clean_architecture_project.userdetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.jesusbadenas.kotlin_clean_architecture_project.R
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseFragment
import com.jesusbadenas.kotlin_clean_architecture_project.common.UIUtils
import com.jesusbadenas.kotlin_clean_architecture_project.databinding.FragmentUserDetailsBinding
import com.jesusbadenas.kotlin_clean_architecture_project.viewmodel.UserDetailsViewModel
import kotlinx.android.synthetic.main.view_retry.*
import org.koin.android.ext.android.inject

/**
 * Fragment that shows details of a certain User.
 */
class UserDetailsFragment : BaseFragment() {

    private val userDetailsVM: UserDetailsViewModel by inject()
    private lateinit var binding: FragmentUserDetailsBinding

    override fun onAttachToContext(context: Context) {
        // do nothing
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Data binding
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_details, container, false)
        binding.lifecycleOwner = this

        // View model
        binding.viewModel = userDetailsVM
        binding.viewProgress.viewModel = userDetailsVM
        binding.viewRetry.viewModel = userDetailsVM
        subscribe()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Retry button
        bt_retry.setOnClickListener {
            loadUserDetails()
        }
    }

    override fun onStart() {
        super.onStart()
        loadUserDetails()
    }

    private fun subscribe() {
        // Error
        userDetailsVM.uiError.observe(viewLifecycleOwner, Observer { resource ->
            UIUtils.showError(context(), resource.data)
        })

        // User details
        userDetailsVM.user.observe(viewLifecycleOwner, Observer { user ->
            binding.user = user
        })
    }

    private fun loadUserDetails() {
        val userId = (activity as UserDetailsActivity).userId
        userDetailsVM.loadUserDetails(userId)
    }
}
