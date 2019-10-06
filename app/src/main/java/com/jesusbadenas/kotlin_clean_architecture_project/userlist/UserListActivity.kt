package com.jesusbadenas.kotlin_clean_architecture_project.userlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.jesusbadenas.kotlin_clean_architecture_project.R
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseActivity
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User

/**
 * Activity that shows a list of Users.
 */
class UserListActivity : BaseActivity(), UserListFragment.UserListListener {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, UserListActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, UserListFragment.newInstance())
        }
    }

    override fun onUserClicked(user: User) {
        navigator.navigateToUserDetails(this, user.userId)
    }
}
