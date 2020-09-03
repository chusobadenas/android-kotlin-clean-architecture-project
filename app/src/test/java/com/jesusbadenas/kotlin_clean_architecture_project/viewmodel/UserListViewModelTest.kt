package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.jesusbadenas.kotlin_clean_architecture_project.data.common.Resource
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserDataMapper
import com.jesusbadenas.kotlin_clean_architecture_project.test.CoroutinesTestRule
import com.jesusbadenas.kotlin_clean_architecture_project.test.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserListViewModelTest {

    companion object {
        private const val USER_ID = 1
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutinesTestRule()

    @MockK
    lateinit var userRepository: UserRepository

    private lateinit var userListVM: UserListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userListVM = UserListViewModel(userRepository, UserDataMapper())
    }

    @Test
    fun testLoadUserListError() {
        val exception = Exception()
        every { userRepository.users() } returns MutableLiveData(Resource.Error(exception))

        val userList = userListVM.userList.getOrAwaitValue()

        assertTrue(userList.isEmpty())
    }

    @Test
    fun testLoadUserListSuccess() {
        val userData = UserData(USER_ID)
        every { userRepository.users() } returns MutableLiveData(Resource.Success(listOf(userData)))

        val userList = userListVM.userList.getOrAwaitValue()

        assertFalse(userList.isNullOrEmpty())
        assertEquals(userList[0].userId, USER_ID)
    }
}
