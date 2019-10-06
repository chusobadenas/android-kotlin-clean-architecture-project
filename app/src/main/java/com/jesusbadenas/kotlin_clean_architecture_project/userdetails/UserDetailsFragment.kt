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
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.jesusbadenas.kotlin_clean_architecture_project.R
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseMvpFragment
import com.jesusbadenas.kotlin_clean_architecture_project.common.UIUtils
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import javax.inject.Inject

/**
 * Fragment that shows details of a certain user.
 */
class UserDetailsFragment : BaseMvpFragment(), UserDetailsMvpView {

    @Inject
    lateinit var userDetailsPresenter: UserDetailsPresenter

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentView = inflater.inflate(R.layout.fragment_user_details, container, false)
        unbinder = ButterKnife.bind(this, fragmentView)
        userDetailsPresenter.attachView(this)
        return fragmentView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder?.unbind()
    }

    override fun onAttachToContext(context: Context) {
        // do nothing
    }

    override fun onStart() {
        super.onStart()
        loadUserDetails()
    }

    override fun onDestroy() {
        super.onDestroy()
        userDetailsPresenter.detachView()
    }

    override fun renderUser(user: User) {
        UIUtils.loadImageUrl(context(), imageViewCover, user.coverUrl)
        textViewFullName.text = user.fullName
        textViewEmail.text = user.email
        textViewFollowers.text = user.followers.toString()
        textViewDescription.text = user.description
    }

    override fun showLoading() {
        viewUserDetail.visibility = View.GONE
        viewProgress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        viewUserDetail.visibility = View.VISIBLE
        viewProgress.visibility = View.GONE
    }

    override fun showRetry() {
        viewRetry.visibility = View.VISIBLE
    }

    override fun hideRetry() {
        viewRetry.visibility = View.GONE
    }

    private fun loadUserDetails() {
        val userId = (activity as UserDetailsActivity).userId
        userDetailsPresenter.initialize(userId)
    }

    @OnClick(R.id.bt_retry)
    fun onButtonRetryClick() {
        loadUserDetails()
    }
}
