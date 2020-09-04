package com.jesusbadenas.kotlin_clean_architecture_project.domain.interactors.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jesusbadenas.kotlin_clean_architecture_project.data.api.APIService
import com.jesusbadenas.kotlin_clean_architecture_project.data.common.Resource
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import com.jesusbadenas.kotlin_clean_architecture_project.test.CoroutinesTestRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserRepositoryTest {

    companion object {
        private const val USER_ID = 1
    }

    private val userData: UserData = UserData(USER_ID)
    private lateinit var userRepository: UserRepository

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutinesTestRule()

    @MockK
    lateinit var apiService: APIService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userRepository = UserRepository(apiService)
    }

    @Test
    fun testGetUsers() {
        val userDataList = arrayListOf(userData)

        coEvery { apiService.userDataList() } returns userDataList

        val result = runBlocking { userRepository.users() }
        val userList = (result as Resource.Success).data!!

        assertTrue(userList.isNotEmpty())
        assertSame(userList.size, 1)
        assertSame(userList[0].userId, USER_ID)
    }

    @Test
    fun testGetUserById() {
        coEvery { apiService.userDataById(USER_ID) } returns userData

        val result = runBlocking { userRepository.user(USER_ID) }
        val user = (result as Resource.Success).data!!

        assertSame(user.userId, USER_ID)
    }
}
