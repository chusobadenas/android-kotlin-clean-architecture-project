package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jesusbadenas.kotlin_clean_architecture_project.domain.interactors.GetUserList
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserEntityMapper
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserListViewModelTest {

    private lateinit var userListVM: UserListViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    lateinit var getUserList: GetUserList

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userListVM = UserListViewModel(getUserList, UserEntityMapper())
    }

    @Test
    fun testLoadUserListSuccess() {
        userListVM.loadUserList()
        assertEquals(userListVM.retryVisibility.value, View.GONE)
        assertEquals(userListVM.loadingVisibility.value, View.VISIBLE)
        verify { getUserList.execute(any(), null) }
    }

    @Test
    fun testOnUserClickedSuccess() {
        val user = User(1)
        userListVM.onUserClicked(user)
        assertEquals(userListVM.userClicked.value?.data?.userId, 1)
    }
}
