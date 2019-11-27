package com.jesusbadenas.kotlin_clean_architecture_project.userdetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.jesusbadenas.kotlin_clean_architecture_project.R
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseMvpFragment
import com.jesusbadenas.kotlin_clean_architecture_project.common.UIError
import com.jesusbadenas.kotlin_clean_architecture_project.common.UIUtils
import com.jesusbadenas.kotlin_clean_architecture_project.viewmodel.UserDetailsViewModel
import javax.inject.Inject

/**
 * Fragment that shows details of a certain User.
 */
class UserDetailsFragment : BaseMvpFragment() {

    private lateinit var userDetailsVM: UserDetailsViewModel

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    @BindView(R.id.iv_cover)
    lateinit var imageViewCover: ImageView
    @BindView(R.id.tv_fullname)
    lateinit var textViewFullName: TextView
    @BindView(R.id.tv_email)
    lateinit var textViewEmail: TextView
    @BindView(R.id.tv_followers)
    lateinit var textViewFollowers: TextView
    @BindView(R.id.tv_description)
    lateinit var textViewDescription: TextView
    @BindView(R.id.rl_progress)
    lateinit var viewProgress: RelativeLayout
    @BindView(R.id.rl_retry)
    lateinit var viewRetry: RelativeLayout
    @BindView(R.id.user_detail_view)
    lateinit var viewUserDetail: LinearLayout

    private var unbinder: Unbinder? = null

    companion object {
        fun newInstance(): UserDetailsFragment {
            return UserDetailsFragment()
        }
    }

    override fun onAttachToContext(context: Context) {
        // do nothing
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentView = inflater.inflate(R.layout.fragment_user_details, container, false)
        unbinder = ButterKnife.bind(this, fragmentView)
        userDetailsVM = ViewModelProviders.of(this, vmFactory).get(UserDetailsViewModel::class.java)
        subscribe()
        return fragmentView
    }

    override fun onStart() {
        super.onStart()
        loadUserDetails()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder?.unbind()
    }

    private fun subscribe() {
        // Loading
        userDetailsVM.isLoading().observe(this, Observer { loading ->
            if (loading) {
                viewUserDetail.visibility = View.GONE
                viewProgress.visibility = View.VISIBLE
            } else {
                viewUserDetail.visibility = View.VISIBLE
                viewProgress.visibility = View.GONE
            }
        })

        // Retry
        userDetailsVM.isRetry().observe(this, Observer { retry ->
            if (retry) {
                viewRetry.visibility = View.VISIBLE
            } else {
                viewRetry.visibility = View.GONE
            }
        })

        // Error
        userDetailsVM.hasError().observe(this, Observer { event ->
            val uiError: UIError = event.peekContent()
            showError(uiError)
        })

        // User details
        userDetailsVM.getUser().observe(this, Observer { user ->
            UIUtils.loadImageUrl(context(), imageViewCover, user.coverUrl)
            textViewFullName.text = user.fullName
            textViewEmail.text = user.email
            textViewFollowers.text = user.followers.toString()
            textViewDescription.text = user.description
        })
    }

    private fun loadUserDetails() {
        val userId = (activity as UserDetailsActivity).userId
        userDetailsVM.loadUserDetails(userId)
    }

    @OnClick(R.id.bt_retry)
    fun onButtonRetryClick() {
        loadUserDetails()
    }
}
