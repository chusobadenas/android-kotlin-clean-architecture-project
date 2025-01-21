package com.jesusbadenas.kotlin_clean_architecture_project.presentation.userlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jesusbadenas.kotlin_clean_architecture_project.domain.model.User
import com.jesusbadenas.kotlin_clean_architecture_project.domain.usecase.GetUsersUseCase
import com.jesusbadenas.kotlin_clean_architecture_project.presentation.R
import com.jesusbadenas.kotlin_clean_architecture_project.presentation.di.presentationTestModule
import com.jesusbadenas.kotlin_clean_architecture_project.test.CustomKoinJUnit4Test
import com.jesusbadenas.kotlin_clean_architecture_project.test.KoinTestApp
import com.jesusbadenas.kotlin_clean_architecture_project.test.extension.getOrAwaitValue
import com.jesusbadenas.kotlin_clean_architecture_project.test.rule.CoroutinesTestRule
import io.mockk.every
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(application = KoinTestApp::class)
class UserListViewModelTest : CustomKoinJUnit4Test(presentationTestModule) {

    @get:Rule
    val coroutineRule = CoroutinesTestRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val getUsersUseCase: GetUsersUseCase by inject()

    private lateinit var viewModel: UserListViewModel

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = UserListViewModel(getUsersUseCase)
    }

    @Test
    fun `test load user list empty`() = coroutineRule.runTest {
        val userListResult = slot<(List<User>?) -> Unit>()
        every {
            getUsersUseCase.invoke(
                scope = any(),
                onResult = capture(userListResult)
            )
        } answers {
            userListResult.captured(emptyList())
        }
        viewModel.loadUserList()
        val uiError = viewModel.uiError.getOrAwaitValue()

        Assert.assertNotNull(uiError)
        Assert.assertEquals(R.string.error_message_empty_list, uiError.messageTextId)
        Assert.assertEquals(R.string.btn_text_retry, uiError.buttonTextId)
    }

    @Test
    fun `test load user list success`() = coroutineRule.runTest {
        val user = User(USER_ID)
        val userListResult = slot<(List<User>?) -> Unit>()
        every {
            getUsersUseCase.invoke(
                scope = any(),
                onResult = capture(userListResult)
            )
        } answers {
            userListResult.captured(listOf(user))
        }

        viewModel.loadUserList()
        val userList = viewModel.userList.getOrAwaitValue()

        Assert.assertEquals(1, userList.size)
        Assert.assertEquals(user, userList[0])
    }

    companion object {
        private const val USER_ID = 1
    }
}
