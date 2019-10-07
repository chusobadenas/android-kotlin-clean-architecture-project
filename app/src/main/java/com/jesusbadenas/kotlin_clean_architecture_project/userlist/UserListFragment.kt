package com.jesusbadenas.kotlin_clean_architecture_project.userlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.jesusbadenas.kotlin_clean_architecture_project.R
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseMvpFragment
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import javax.inject.Inject

/**
 * Fragment that shows a list of Users.
 */
class UserListFragment : BaseMvpFragment(), UserListMvpView {

    @Inject
    lateinit var userListPresenter: UserListPresenter
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
            userListPresenter.onUserClicked(user)
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
        userListPresenter.attachView(this)
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

    override fun onDestroy() {
        super.onDestroy()
        userListPresenter.detachView()
    }

    override fun showLoading() {
        swipeRefresh.visibility = View.GONE
        swipeRefresh.isRefreshing = true
        viewProgress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        swipeRefresh.visibility = View.VISIBLE
        swipeRefresh.isRefreshing = false
        viewProgress.visibility = View.GONE
    }

    override fun showRetry() {
        viewRetry.visibility = View.VISIBLE
    }

    override fun hideRetry() {
        viewRetry.visibility = View.GONE
    }

    override fun showUserList(users: List<User>) {
        usersAdapter.setUsers(users)
    }

    override fun viewUser(user: User) {
        userListListener?.onUserClicked(user)
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

    private fun loadUserList() {
        userListPresenter.initialize()
    }

    @OnClick(R.id.bt_retry)
    fun onButtonRetryClick() {
        loadUserList()
    }
}
