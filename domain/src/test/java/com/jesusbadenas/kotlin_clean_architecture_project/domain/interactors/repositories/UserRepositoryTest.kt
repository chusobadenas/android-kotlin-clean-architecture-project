package com.jesusbadenas.kotlin_clean_architecture_project.domain.interactors.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jesusbadenas.kotlin_clean_architecture_project.data.api.APIService
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
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

    @MockK
    lateinit var apiService: APIService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userRepository = UserRepository(apiService)
    }

    @Test
    fun testGetUsers() = runBlockingTest {
        val userDataList = arrayListOf(userData)

        coEvery { apiService.userDataList() } returns userDataList

        val userList = userRepository.users()

        assertTrue(userList.isNotEmpty())
        assertSame(userList.size, 1)
        assertSame(userList[0].userId, USER_ID)
    }

    @Test
    fun testGetUserById() = runBlockingTest {
        coEvery { apiService.userDataById(USER_ID) } returns userData

        val user = userRepository.user(USER_ID)

        assertSame(user.userId, USER_ID)
    }
}
