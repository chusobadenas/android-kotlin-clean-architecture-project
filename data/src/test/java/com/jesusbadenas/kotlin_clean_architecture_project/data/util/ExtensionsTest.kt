package com.jesusbadenas.kotlin_clean_architecture_project.data.util

import com.jesusbadenas.kotlin_clean_architecture_project.data.api.response.UserResponse
import com.jesusbadenas.kotlin_clean_architecture_project.domain.model.User
import org.junit.Assert
import org.junit.Test

class ExtensionsTest {

    @Test
    fun testUserResponseConversionSuccess() {
        val userResponse = UserResponse(
            userId = 1,
            coverUrl = "https://localhost/images/1",
            fullName = "John Doe",
            email = "john.doe@example.com",
            description = "",
            followers = 10
        )

        val result = userResponse.toUser()
        val expected = User(
            userId = 1,
            coverUrl = "https://localhost/images/1",
            fullName = "John Doe",
            email = "john.doe@example.com",
            description = "",
            followers = 10
        )

        Assert.assertEquals(expected, result)
    }
}
