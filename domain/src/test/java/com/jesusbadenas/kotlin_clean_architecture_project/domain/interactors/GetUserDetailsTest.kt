package com.jesusbadenas.kotlin_clean_architecture_project.domain.interactors

import com.jesusbadenas.kotlin_clean_architecture_project.domain.entities.UserEntity
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class GetUserDetailsTest {

    companion object {
        private const val USER_ID = 1
    }

    lateinit var getUserDetails: GetUserDetails

    @MockK
    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getUserDetails = GetUserDetails(userRepository)
    }

    @Test
    fun testGetUserById() {
        val observable = Observable.empty<UserEntity>()
        every { userRepository.user(USER_ID) } returns observable

        val params = hashMapOf("id" to USER_ID)
        getUserDetails.create(params)

        verify { userRepository.user(USER_ID) }
    }
}
