package com.jesusbadenas.kotlin_clean_architecture_project.entities.mapper

import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.toUser
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class UserDataMapperTest {

    companion object {
        private const val COVER_URL = "coverUrl"
        private const val DESCRIPTION = "description"
        private const val EMAIL = "email"
        private const val FULL_NAME = "fullName"
        private const val FOLLOWERS = 10
        private const val USER_ID = 1
    }

    private val userData: UserData = UserData(USER_ID)

    init {
        userData.coverUrl = COVER_URL
        userData.description = DESCRIPTION
        userData.email = EMAIL
        userData.followers = FOLLOWERS
        userData.fullName = FULL_NAME
    }

    @Test
    fun testTransformToUser() {
        val user = userData.toUser()

        assertEquals(user.coverUrl, COVER_URL)
        assertEquals(user.description, DESCRIPTION)
        assertEquals(user.email, EMAIL)
        assertSame(user.followers, FOLLOWERS)
        assertEquals(user.fullName, FULL_NAME)
        assertSame(user.userId, USER_ID)
    }
}
