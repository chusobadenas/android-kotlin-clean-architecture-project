package com.jesusbadenas.kotlin_clean_architecture_project.userdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.jesusbadenas.kotlin_clean_architecture_project.R
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseActivity

/**
 * Activity that shows details of a certain user.
 */
class UserDetailsActivity : BaseActivity() {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    var userId: Int = -1

    companion object {
        private const val INTENT_PARAM_USER_ID = "INTENT_PARAM_USER_ID"
        private const val STATE_PARAM_USER_ID = "STATE_PARAM_USER_ID"

        fun getCallingIntent(context: Context, userId: Int): Intent {
            val callingIntent = Intent(context, UserDetailsActivity::class.java)
            callingIntent.putExtra(INTENT_PARAM_USER_ID, userId)
            return callingIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        this.initializeActivity(savedInstanceState)
        ButterKnife.bind(this)
        setupToolbar()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_PARAM_USER_ID, this.userId)
        super.onSaveInstanceState(outState)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initializeActivity(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            userId = it.getInt(STATE_PARAM_USER_ID)
        } ?: run {
            userId = intent.getIntExtra(INTENT_PARAM_USER_ID, -1)
            addFragment(R.id.fragmentContainer, UserDetailsFragment.newInstance())
        }
    }
}
