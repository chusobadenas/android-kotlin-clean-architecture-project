package com.jesusbadenas.kotlin_clean_architecture_project.di

import com.jesusbadenas.kotlin_clean_architecture_project.data.repositories.UserDataRepository
import com.jesusbadenas.kotlin_clean_architecture_project.di.modules.UserModule
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class UserModuleTest {

    private lateinit var userModule: UserModule

    @MockK
    lateinit var userDataRepository: UserDataRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userModule = UserModule()
    }

    @Test
    fun testProvideGetUserList() {
        assertNotNull(userModule.provideGetUserListUseCase(userDataRepository))
    }

    @Test
    fun testProvideGetUserDetailsUseCase() {
        assertNotNull(userModule.provideGetUserDetailsUseCase(userDataRepository))
    }
}
