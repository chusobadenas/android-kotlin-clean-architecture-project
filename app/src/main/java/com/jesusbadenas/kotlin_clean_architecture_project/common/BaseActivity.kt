package com.jesusbadenas.kotlin_clean_architecture_project.common

import android.content.Context
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.jesusbadenas.kotlin_clean_architecture_project.navigation.Navigator
import org.koin.android.ext.android.inject

/**
 * Base [AppCompatActivity] class for every Activity in this application.
 */
abstract class BaseActivity : AppCompatActivity() {

    protected val navigator: Navigator by inject()

    fun addFragment(containerViewId: Int, fragmentClass: Class<out Fragment>) {
        supportFragmentManager.beginTransaction().apply {
            add(containerViewId, fragmentClass, null, null)
            commit()
        }
    }

    fun replaceFragment(
        containerViewId: Int,
        fragmentClass: Class<out Fragment>,
        addToBackStack: Boolean
    ) {
        supportFragmentManager.beginTransaction().apply {
            replace(containerViewId, fragmentClass, null, null)
            if (addToBackStack) {
                addToBackStack(null)
            }
            commit()
        }
    }

    fun getCurrentFragment(containerId: Int): Fragment? =
        supportFragmentManager.findFragmentById(containerId)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var result = super.onOptionsItemSelected(item)
        if (android.R.id.home == item.itemId) {
            onBackPressed()
            result = true
        }
        return result
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    fun context(): Context = this
}
