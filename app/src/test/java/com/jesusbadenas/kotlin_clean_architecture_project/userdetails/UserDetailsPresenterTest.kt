package com.jesusbadenas.kotlin_clean_architecture_project.userdetails

import com.jesusbadenas.kotlin_clean_architecture_project.domain.interactors.GetUserDetails
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserEntityMapper
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class UserDetailsPresenterTest {

    private lateinit var userDetailsPresenter: UserDetailsPresenter

    @MockK(relaxed = true)
    lateinit var getUserDetails: GetUserDetails
    @MockK(relaxed = true)
    lateinit var userDetailsMvpView: UserDetailsMvpView

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userDetailsPresenter = UserDetailsPresenter(getUserDetails, UserEntityMapper())
        userDetailsPresenter.attachView(userDetailsMvpView)
    }

    @Test
    fun testAttachViewSuccess() {
        assertNotNull(userDetailsPresenter.mvpView)
    }

    @Test
    fun testDetachViewSuccess() {
        userDetailsPresenter.detachView()
        assertNull(userDetailsPresenter.mvpView)
        verify { getUserDetails.unsubscribe() }
    }

    @Test
    fun testInitializeSuccess() {
        userDetailsPresenter.initialize(1)
        verify { userDetailsMvpView.hideRetry() }
        verify { userDetailsMvpView.showLoading() }
        verify { getUserDetails.execute(any(), eq(hashMapOf("id" to 1))) }
    }
}
