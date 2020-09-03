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
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.viewmodel.UserDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * Fragment that shows details of a certain User.
 */
class UserDetailsFragment : BaseFragment() {

    private val userDetailsVM: UserDetailsViewModel by viewModel {
        parametersOf((activity as UserDetailsActivity).userId)
    }

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

    private fun subscribe() {
        // TODO: Error
        userDetailsVM.uiError.observe(viewLifecycleOwner, Observer { error ->
            // showError(exception, "Error loading user details", null, null)
            UIUtils.showError(context(), error)
        })

        // Retry
        userDetailsVM.retryAction.observe(viewLifecycleOwner, Observer {
            loadUserDetails(userDetailsVM.user.value)
        })

        // User details
        userDetailsVM.user.observe(viewLifecycleOwner, Observer { user ->
            loadUserDetails(user)
        })
    }

    private fun loadUserDetails(user: User?) {
        binding.user = user
    }
}
