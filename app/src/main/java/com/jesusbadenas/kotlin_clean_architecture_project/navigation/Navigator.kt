package com.jesusbadenas.kotlin_clean_architecture_project.navigation

import android.content.Context
import com.jesusbadenas.kotlin_clean_architecture_project.userdetails.UserDetailsActivity
import com.jesusbadenas.kotlin_clean_architecture_project.userlist.UserListActivity

/**
 * Class used to navigate through the application.
 */
class Navigator {

    fun navigateToUserList(context: Context) {
        val intentToLaunch = UserListActivity.getCallingIntent(context)
        context.startActivity(intentToLaunch)
    }

    fun navigateToUserDetails(context: Context, userId: Int) {
        val intentToLaunch = UserDetailsActivity.getCallingIntent(context, userId)
        context.startActivity(intentToLaunch)
    }
}
