package com.jesusbadenas.kotlin_clean_architecture_project.userlist

import com.jesusbadenas.kotlin_clean_architecture_project.domain.interactors.GetUserList
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserEntityMapper
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class UserListPresenterTest {

    private lateinit var userListPresenter: UserListPresenter

    @MockK(relaxed = true)
    lateinit var getUserList: GetUserList
    @MockK(relaxed = true)
    lateinit var userListMvpView: UserListMvpView

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userListPresenter = UserListPresenter(getUserList, UserEntityMapper())
        userListPresenter.attachView(userListMvpView)
    }

    @Test
    fun testAttachViewSuccess() {
        assertNotNull(userListPresenter.mvpView)
    }

    @Test
    fun testDetachViewSuccess() {
        userListPresenter.detachView()
        assertNull(userListPresenter.mvpView)
        verify { getUserList.unsubscribe() }
    }

    @Test
    fun testInitializeSuccess() {
        userListPresenter.initialize()
        verify { userListMvpView.hideRetry() }
        verify { userListMvpView.showLoading() }
        verify { getUserList.execute(any(), null) }
    }

    @Test
    fun testOnUserClickedSuccess() {
        val user = User(1)
        userListPresenter.onUserClicked(user)
        verify { userListMvpView.viewUser(user) }
    }
}
