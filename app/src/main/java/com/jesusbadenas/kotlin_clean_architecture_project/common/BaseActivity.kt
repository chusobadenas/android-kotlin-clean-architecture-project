package com.jesusbadenas.kotlin_clean_architecture_project.common

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.jesusbadenas.kotlin_clean_architecture_project.R
import com.jesusbadenas.kotlin_clean_architecture_project.navigation.Navigator
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

/**
 * Base [AppCompatActivity] class for every Activity in this application.
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    fun addFragment(containerViewId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(containerViewId, fragment)
            .commit()
    }

    fun replaceFragment(containerViewId: Int, fragment: Fragment, addToBackStack: Boolean) {
        var transaction =
            supportFragmentManager.beginTransaction().replace(containerViewId, fragment)
        if (addToBackStack) {
            transaction = transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    fun getCurrentFragment(containerId: Int): Fragment? {
        return supportFragmentManager.findFragmentById(containerId)
    }

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

    fun showError(uiError: UIError) {
        // Show log message
        Timber.e(uiError.throwable, uiError.logMessage)

        // Show dialog
        val message = uiError.errorMsgId?.let {
            getString(it)
        } ?: getString(R.string.error_message_generic)

        val title = getString(R.string.error_title_generic)

        DialogFactory.showDialog(
            this,
            DialogFactory.DialogType.SIMPLE,
            title,
            message,
            android.R.string.ok,
            uiError.action
        )
    }
}
