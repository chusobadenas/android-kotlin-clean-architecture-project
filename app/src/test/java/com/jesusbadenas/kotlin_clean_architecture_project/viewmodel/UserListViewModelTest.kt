package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserDataMapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserListViewModelTest {

    private lateinit var userListVM: UserListViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userListVM = UserListViewModel(userRepository, UserDataMapper())
    }

    @Test
    fun testLoadUserListSuccess() = runBlockingTest {
        val userData = mockk<UserData>()
        every { userData.userId } returns 1
        val dataUsers = listOf(userData)
        coEvery { userRepository.users() } returns dataUsers

        userListVM.loadUserList()

        assertEquals(userListVM.retryVisibility.value, View.GONE)
        assertEquals(userListVM.loadingVisibility.value, View.VISIBLE)
        assertTrue(userListVM.userList.value!!.isNotEmpty())
    }
}
