package com.jesusbadenas.kotlin_clean_architecture_project.userlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
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
import com.jesusbadenas.kotlin_clean_architecture_project.common.UIError
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.viewmodel.UserListViewModel
import javax.inject.Inject

/**
 * Fragment that shows a list of Users.
 */
class UserListFragment : BaseFragment() {

    private lateinit var userListVM: UserListViewModel

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    @Inject
    lateinit var usersAdapter: UserAdapter

    @BindView(R.id.rv_users)
    lateinit var viewUsers: RecyclerView
    @BindView(R.id.rl_progress)
    lateinit var viewProgress: RelativeLayout
    @BindView(R.id.rl_retry)
    lateinit var viewRetry: RelativeLayout
    @BindView(R.id.swipe_container)
    lateinit var swipeRefresh: SwipeRefreshLayout

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
        val fragmentView = inflater.inflate(R.layout.fragment_user_list, container, false)
        unbinder = ButterKnife.bind(this, fragmentView)
        userListVM = ViewModelProviders.of(this, vmFactory).get(UserListViewModel::class.java)
        subscribe()
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
        viewUsers.layoutManager = UsersLayoutManager(context())
        viewUsers.adapter = usersAdapter

        swipeRefresh.setColorSchemeResources(R.color.primary)
        swipeRefresh.setOnRefreshListener {
            loadUserList()
        }
    }

    private fun subscribe() {
        // Loading
        userListVM.isLoading().observe(this, Observer { loading ->
            swipeRefresh.isRefreshing = loading
            if (loading) {
                swipeRefresh.visibility = View.GONE
                viewProgress.visibility = View.VISIBLE
            } else {
                swipeRefresh.visibility = View.VISIBLE
                viewProgress.visibility = View.GONE
            }
        })

        // Retry
        userListVM.isRetry().observe(this, Observer { retry ->
            if (retry) {
                viewRetry.visibility = View.VISIBLE
            } else {
                viewRetry.visibility = View.GONE
            }
        })

        // Error
        userListVM.hasError().observe(this, Observer { event ->
            val uiError: UIError = event.peekContent()
            showError(uiError)
        })

        // User list
        userListVM.getUserList().observe(this, Observer { users ->
            usersAdapter.setUsers(users)
        })

        // User clicked
        userListVM.getUserClicked().observe(this, Observer { event ->
            val user: User = event.peekContent()
            userListListener?.onUserClicked(user)
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
