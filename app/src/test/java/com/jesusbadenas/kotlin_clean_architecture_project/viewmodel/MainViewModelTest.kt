package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var mainVM: MainViewModel

    @Before
    fun setUp() {
        mainVM = MainViewModel()
    }

    @Test
    fun testOnLoadButtonClick() {
        mainVM.onLoadButtonClick()
        assertNull(mainVM.loadAction.value)
    }
}
