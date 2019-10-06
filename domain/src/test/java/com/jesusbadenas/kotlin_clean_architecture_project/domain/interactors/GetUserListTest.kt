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

class GetUserListTest {

    lateinit var getUserList: GetUserList

    @MockK
    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getUserList = GetUserList(userRepository)
    }

    @Test
    fun testGetUsers() {
        val observable = Observable.empty<List<UserEntity>>()
        every { userRepository.users() } returns observable

        getUserList.create(null)

        verify { userRepository.users() }
    }
}
