package com.jesusbadenas.kotlin_clean_architecture_project.userlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jesusbadenas.kotlin_clean_architecture_project.R
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseFragment
import com.jesusbadenas.kotlin_clean_architecture_project.common.UIUtils
import com.jesusbadenas.kotlin_clean_architecture_project.databinding.FragmentUserListBinding
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.viewmodel.UserListViewModel
import kotlinx.android.synthetic.main.fragment_user_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment that shows a list of Users.
 */
class UserListFragment : BaseFragment(), UserAdapter.OnItemClickListener {

    private val usersAdapter: UserAdapter by inject()
    private val userListVM: UserListViewModel by viewModel()

    private lateinit var binding: FragmentUserListBinding

    interface UserListListener {
        fun onUserClicked(user: User)
    }

    private var userListListener: UserListListener? = null

    override fun onUserItemClicked(user: User) {
        userListListener?.onUserClicked(user)
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
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rv_users.adapter = null
    }

    private fun setupRecyclerView() {
        usersAdapter.onItemClickListener = this
        rv_users.apply {
            layoutManager = UsersLayoutManager(context())
            adapter = usersAdapter
        }

        swipe_container.setColorSchemeResources(R.color.primary)
        swipe_container.setOnRefreshListener {
            loadUserList(userListVM.userList.value)
        }
    }

    private fun subscribe() {
        userListVM.uiError.observe(viewLifecycleOwner) { error ->
            UIUtils.showError(context(), error)
        }

        // Retry
        userListVM.retryAction.observe(viewLifecycleOwner) {
            loadUserList(userListVM.userList.value)
        }

        // User list
        userListVM.userList.observe(viewLifecycleOwner) { users ->
            loadUserList(users)
        }
    }

    private fun loadUserList(users: List<User>?) {
        userListVM.showLoading(View.GONE)
        userListVM.showRetry(View.GONE)
        if (swipe_container.isRefreshing) {
            swipe_container.isRefreshing = false
        }
        usersAdapter.submitList(users)
    }
}
