package com.jesusbadenas.kotlin_clean_architecture_project.userlist

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
import com.jesusbadenas.kotlin_clean_architecture_project.databinding.FragmentUserListBinding
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.viewmodel.UserListViewModel
import kotlinx.android.synthetic.main.fragment_user_list.*
import kotlinx.android.synthetic.main.view_retry.*
import org.koin.android.ext.android.inject

/**
 * Fragment that shows a list of Users.
 */
class UserListFragment : BaseFragment() {

    private val usersAdapter: UserAdapter by inject()
    private val userListVM: UserListViewModel by inject()

    private lateinit var binding: FragmentUserListBinding

    private var userListListener: UserListListener? = null

    private val onItemClickListener = object : UserAdapter.OnItemClickListener {
        override fun onUserItemClicked(user: User) {
            userListVM.onUserClicked(user)
        }
    }

    interface UserListListener {
        fun onUserClicked(user: User)
    }

    override fun onAttachToContext(context: Context) {
        if (context is UserListListener) {
            userListListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Data binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list, container, false)
        binding.lifecycleOwner = this

        // View model
        binding.viewModel = userListVM
        binding.viewProgress.viewModel = userListVM
        binding.viewRetry.viewModel = userListVM
        subscribe()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Initialize list
        setupRecyclerView()
        // Retry button
        bt_retry.setOnClickListener {
            loadUserList()
        }
    }

    override fun onStart() {
        super.onStart()
        loadUserList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rv_users.adapter = null
    }

    private fun setupRecyclerView() {
        usersAdapter.setOnItemClickListener(onItemClickListener)
        rv_users.apply {
            layoutManager = UsersLayoutManager(context())
            adapter = usersAdapter
        }

        swipe_container.setColorSchemeResources(R.color.primary)
        swipe_container.setOnRefreshListener {
            loadUserList()
        }
    }

    private fun subscribe() {
        // Error
        userListVM.uiError.observe(viewLifecycleOwner, Observer { resource ->
            UIUtils.showError(context(), resource.data)
        })

        // User list
        userListVM.userList.observe(viewLifecycleOwner, Observer { users ->
            usersAdapter.setUsers(users)
        })

        // User clicked
        userListVM.userClicked.observe(viewLifecycleOwner, Observer { resource ->
            userListListener?.onUserClicked(resource.data)
        })
    }

    private fun loadUserList() {
        userListVM.loadUserList()
    }
}
