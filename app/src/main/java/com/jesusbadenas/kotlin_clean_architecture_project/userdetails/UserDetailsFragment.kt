package com.jesusbadenas.kotlin_clean_architecture_project.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
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

    private val navArgs: UserDetailsFragmentArgs by navArgs()

    private val userDetailsVM: UserDetailsViewModel by viewModel {
        parametersOf(navArgs.userId)
    }

    private lateinit var binding: FragmentUserDetailsBinding

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
        userDetailsVM.uiError.observe(viewLifecycleOwner) { error ->
            UIUtils.showError(context(), error)
        }

        userDetailsVM.retryAction.observe(viewLifecycleOwner) {
            userDetailsVM.loadUser()
        }

        // User details
        userDetailsVM.user.observe(viewLifecycleOwner) { user ->
            loadUserDetails(user)
        }
    }

    private fun loadUserDetails(user: User?) {
        userDetailsVM.showLoading(View.GONE)
        userDetailsVM.showRetry(userDetailsVM.retryVisibility.value!!)
        binding.user = user
    }
}
