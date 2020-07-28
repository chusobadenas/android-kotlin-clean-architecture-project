package com.jesusbadenas.kotlin_clean_architecture_project.data.entities.mappers

import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import org.junit.Assert.*
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

    private val userDataMapper: UserDataMapper = UserDataMapper()
    private val userData: UserData = UserData(USER_ID)

    init {
        userData.coverUrl = COVER_URL
        userData.description = DESCRIPTION
        userData.email = EMAIL
        userData.followers = FOLLOWERS
        userData.fullName = FULL_NAME
    }

    @Test
    fun testTransformToUserEntity() {
        val userEntity = userDataMapper.transform(userData)

        assertEquals(userEntity.coverUrl, COVER_URL)
        assertEquals(userEntity.description, DESCRIPTION)
        assertEquals(userEntity.email, EMAIL)
        assertSame(userEntity.followers, FOLLOWERS)
        assertEquals(userEntity.fullName, FULL_NAME)
        assertSame(userEntity.userId, USER_ID)
    }

    @Test
    fun testTransformToUserEntityCollection() {
        val userDataList = arrayListOf(userData)

        val userEntities = userDataMapper.transform(userDataList)

        assertTrue(userEntities.isNotEmpty())
        assertSame(userEntities.size, 1)

        val userEntity = userEntities[0]
        assertEquals(userEntity.coverUrl, COVER_URL)
        assertEquals(userEntity.description, DESCRIPTION)
        assertEquals(userEntity.email, EMAIL)
        assertSame(userEntity.followers, FOLLOWERS)
        assertEquals(userEntity.fullName, FULL_NAME)
        assertSame(userEntity.userId, USER_ID)
    }
}
