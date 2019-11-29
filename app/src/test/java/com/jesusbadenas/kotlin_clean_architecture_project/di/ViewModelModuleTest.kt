package com.jesusbadenas.kotlin_clean_architecture_project.di

import com.jesusbadenas.kotlin_clean_architecture_project.di.modules.ViewModelModule
import com.jesusbadenas.kotlin_clean_architecture_project.domain.interactors.GetUserDetails
import com.jesusbadenas.kotlin_clean_architecture_project.domain.interactors.GetUserList
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserEntityMapper
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ViewModelModuleTest {

    private lateinit var viewModelModule: ViewModelModule

    @MockK
    lateinit var getUserList: GetUserList
    @MockK
    lateinit var getUserDetails: GetUserDetails

    private val userEntityMapper = UserEntityMapper()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModelModule = ViewModelModule()
    }

    @Test
    fun testProvideUserListViewModel() {
        Assert.assertNotNull(
            viewModelModule.provideUserListViewModel(
                getUserList,
                userEntityMapper
            )
        )
    }

    @Test
    fun testProvideUserDetailsViewModel() {
        Assert.assertNotNull(
            viewModelModule.provideUserDetailsViewModel(
                getUserDetails,
                userEntityMapper
            )
        )
    }
}