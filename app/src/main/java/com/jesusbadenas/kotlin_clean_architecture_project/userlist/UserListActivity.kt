package com.jesusbadenas.kotlin_clean_architecture_project.userlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jesusbadenas.kotlin_clean_architecture_project.R
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseActivity
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Activity that shows a list of Users.
 */
class UserListActivity : BaseActivity(), UserListFragment.UserListListener {

    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, UserListActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, UserListFragment::class.java)
        }
    }

    override fun onUserClicked(user: User) {
        navigator.navigateToUserDetails(this, user.userId)
    }
}
