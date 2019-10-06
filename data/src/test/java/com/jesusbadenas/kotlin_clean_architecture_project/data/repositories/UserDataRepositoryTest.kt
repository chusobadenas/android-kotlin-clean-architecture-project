package com.jesusbadenas.kotlin_clean_architecture_project.data.repositories

import com.jesusbadenas.kotlin_clean_architecture_project.data.api.APIService
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.mappers.UserDataMapper
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UserDataRepositoryTest {

    companion object {
        private const val USER_ID = 1
    }

    private val userData: UserData = UserData(USER_ID)
    lateinit var userDataRepository: UserDataRepository

    @MockK
    lateinit var apiService: APIService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userDataRepository = UserDataRepository(apiService, UserDataMapper())
    }

    @Test
    fun testGetUsers() {
        val userDataList = arrayListOf(userData)
        val observableUserDataList = Observable.just<List<UserData>>(userDataList)

        every { apiService.userDataList() } returns observableUserDataList

        val observable = userDataRepository.users()
        val testObserver = observable.test()
        testObserver.assertNoErrors()
        val userList = testObserver.values()[0]

        assertTrue(userList.isNotEmpty())
        assertSame(userList.size, 1)
        assertSame(userList[0].userId, USER_ID)
    }

    @Test
    fun testGetUserById() {
        val observableUserData = Observable.just<UserData>(userData)

        every { apiService.userDataById(USER_ID) } returns observableUserData

        val observable = userDataRepository.user(USER_ID)
        val testObserver = observable.test()
        testObserver.assertNoErrors()
        val user = testObserver.values()[0]

        assertSame(user.userId, USER_ID)
    }
}
