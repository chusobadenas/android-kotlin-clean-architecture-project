package com.jesusbadenas.kotlin_clean_architecture_project.userdetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import butterknife.OnClick
import butterknife.Unbinder
import com.jesusbadenas.kotlin_clean_architecture_project.R
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseFragment
import com.jesusbadenas.kotlin_clean_architecture_project.common.UIUtils
import com.jesusbadenas.kotlin_clean_architecture_project.databinding.FragmentUserDetailsBinding
import com.jesusbadenas.kotlin_clean_architecture_project.viewmodel.UserDetailsViewModel
import javax.inject.Inject

/**
 * Fragment that shows details of a certain User.
 */
class UserDetailsFragment : BaseFragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private lateinit var userDetailsVM: UserDetailsViewModel
    private lateinit var binding: FragmentUserDetailsBinding
    private var unbinder: Unbinder? = null

    companion object {
        fun newInstance(): UserDetailsFragment {
            return UserDetailsFragment()
        }
    }

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
        userDetailsVM = ViewModelProviders.of(this, vmFactory).get(UserDetailsViewModel::class.java)
        binding.viewModel = userDetailsVM
        binding.viewProgress.viewModel = userDetailsVM
        binding.viewRetry.viewModel = userDetailsVM
        subscribe()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        loadUserDetails()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder?.unbind()
    }

    private fun subscribe() {
        // Error
        userDetailsVM.getUIError().observe(this, Observer { resource ->
            UIUtils.showError(context(), resource.data)
        })

        // User details
        userDetailsVM.getUser().observe(this, Observer { user ->
            binding.user = user
        })
    }

    private fun loadUserDetails() {
        val userId = (activity as UserDetailsActivity).userId
        userDetailsVM.loadUserDetails(userId)
    }

    @OnClick(R.id.bt_retry)
    fun onButtonRetryClick() {
        loadUserDetails()
    }
}
