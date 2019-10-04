package com.jesusbadenas.kotlin_clean_architecture_project.navigation

import android.content.Context
import com.jesusbadenas.kotlin_clean_architecture_project.userdetails.UserDetailsActivity
import com.jesusbadenas.kotlin_clean_architecture_project.userlist.UserListActivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class used to navigate through the application.
 */
@Singleton
class Navigator
@Inject
constructor() {

    fun navigateToUserList(context: Context?) {
        if (context != null) {
            val intentToLaunch = UserListActivity.getCallingIntent(context)
            context.startActivity(intentToLaunch)
        }
    }

    fun navigateToUserDetails(context: Context?, userId: Int) {
        if (context != null) {
            val intentToLaunch = UserDetailsActivity.getCallingIntent(context, userId)
            context.startActivity(intentToLaunch)
        }
    }
}
