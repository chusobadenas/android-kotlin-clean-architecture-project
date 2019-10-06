package com.jesusbadenas.kotlin_clean_architecture_project.di

import android.content.Context
import com.jesusbadenas.kotlin_clean_architecture_project.App
import com.jesusbadenas.kotlin_clean_architecture_project.data.repositories.UserDataRepository
import com.jesusbadenas.kotlin_clean_architecture_project.di.modules.ApplicationModule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class ApplicationModuleTest {

    lateinit var applicationModule: ApplicationModule

    @MockK
    lateinit var application: App
    @MockK
    lateinit var context: Context
    @MockK
    lateinit var userDataRepository: UserDataRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        applicationModule = ApplicationModule()
        every { application.applicationContext } returns context
    }

    @Test
    fun testProvideApplicationContextSuccess() {
        val ctx = applicationModule.provideContext(application)
        assertNotNull(ctx)
        assertEquals(ctx, context)
    }

    @Test
    fun testProvideAPIServiceSuccess() {
        val apiService = applicationModule.provideApiService()
        assertNotNull(apiService)
    }

    @Test
    fun testProvideUserRepositorySuccess() {
        assertEquals(
            applicationModule.provideUserRepository(userDataRepository),
            userDataRepository
        )
    }
}
