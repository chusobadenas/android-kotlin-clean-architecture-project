package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jesusbadenas.kotlin_clean_architecture_project.domain.interactors.GetUserDetails
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserEntityMapper
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserDetailsViewModelTest {

    private lateinit var userDetailsVM: UserDetailsViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    lateinit var getUserDetails: GetUserDetails

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userDetailsVM = UserDetailsViewModel(getUserDetails, UserEntityMapper())
    }

    @Test
    fun testLoadUserDetailsSuccess() {
        userDetailsVM.loadUserDetails(1)
        Assert.assertFalse(userDetailsVM.isRetry().value!!)
        Assert.assertTrue(userDetailsVM.isLoading().value!!)
        verify { getUserDetails.execute(any(), eq(hashMapOf("id" to 1))) }
    }
}
