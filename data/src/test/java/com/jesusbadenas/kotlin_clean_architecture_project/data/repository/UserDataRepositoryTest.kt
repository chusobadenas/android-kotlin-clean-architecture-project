package com.jesusbadenas.kotlin_clean_architecture_project.data.repository

import com.jesusbadenas.kotlin_clean_architecture_project.data.api.APIService
import com.jesusbadenas.kotlin_clean_architecture_project.data.api.response.UserResponse
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repository.UserRepository
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
class UserDataRepositoryTest {

    private val userResponse = UserResponse(USER_ID)
    private lateinit var userDataRepository: UserRepository

    @get:Rule
    val coroutineRule = CoroutinesTestRule()

    @MockK
    private lateinit var apiService: APIService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userDataRepository = UserDataRepository(apiService)
    }

    @Test
    fun testGetUsers() {
        coEvery { apiService.userDataList() } returns listOf(userResponse)

        val result = runBlocking { userDataRepository.users() }

        assertTrue(result.isNotEmpty())
        assertSame(result.size, 1)
        assertSame(result[0].userId, USER_ID)
    }

    @Test
    fun testGetUserById() {
        coEvery { apiService.userDataById(USER_ID) } returns userResponse

        val result = runBlocking { userDataRepository.user(USER_ID) }

        assertSame(result.userId, USER_ID)
    }

    companion object {
        private const val USER_ID = 1
    }
}
