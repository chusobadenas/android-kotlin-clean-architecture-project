package com.jesusbadenas.kotlin_clean_architecture_project.userlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.jesusbadenas.kotlin_clean_architecture_project.R
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseFragment
import com.jesusbadenas.kotlin_clean_architecture_project.common.UIUtils
import com.jesusbadenas.kotlin_clean_architecture_project.databinding.FragmentUserListBinding
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.viewmodel.UserListViewModel
import javax.inject.Inject

/**
 * Fragment that shows a list of Users.
 */
class UserListFragment : BaseFragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    @Inject
    lateinit var usersAdapter: UserAdapter

    @BindView(R.id.rv_users)
    lateinit var viewUsers: RecyclerView
    @BindView(R.id.swipe_container)
    lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var userListVM: UserListViewModel
    private lateinit var binding: FragmentUserListBinding

    private var userListListener: UserListListener? = null
    private var unbinder: Unbinder? = null

    companion object {
        fun newInstance(): UserListFragment {
            return UserListFragment()
        }
    }

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

        // Butterknife
        val fragmentView = binding.root
        unbinder = ButterKnife.bind(this, fragmentView)

        // View model
        userListVM = ViewModelProviders.of(this, vmFactory).get(UserListViewModel::class.java)
        binding.viewModel = userListVM
        binding.viewProgress.viewModel = userListVM
        binding.viewRetry.viewModel = userListVM
        subscribe()

        // Initialize list
        setupRecyclerView()

        return fragmentView
    }

    override fun onStart() {
        super.onStart()
        loadUserList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewUsers.adapter = null
        unbinder?.unbind()
    }

    private fun setupRecyclerView() {
        usersAdapter.setOnItemClickListener(onItemClickListener)
        viewUsers.apply {
            layoutManager = UsersLayoutManager(context())
            adapter = usersAdapter
        }

        swipeRefresh.setColorSchemeResources(R.color.primary)
        swipeRefresh.setOnRefreshListener {
            loadUserList()
        }
    }

    private fun subscribe() {
        // Error
        userListVM.getUIError().observe(this, Observer { resource ->
            UIUtils.showError(context(), resource.data)
        })

        // User list
        userListVM.getUserList().observe(this, Observer { users ->
            usersAdapter.setUsers(users)
        })

        // User clicked
        userListVM.getUserClicked().observe(this, Observer { resource ->
            userListListener?.onUserClicked(resource.data)
        })
    }

    private fun loadUserList() {
        userListVM.loadUserList()
    }

    @OnClick(R.id.bt_retry)
    fun onButtonRetryClick() {
        loadUserList()
    }
}
