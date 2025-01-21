package com.jesusbadenas.kotlin_clean_architecture_project.data.util

import com.jesusbadenas.kotlin_clean_architecture_project.data.api.model.UserDTO
import com.jesusbadenas.kotlin_clean_architecture_project.data.db.model.UserEntity
import com.jesusbadenas.kotlin_clean_architecture_project.domain.model.User
import org.junit.jupiter.api.Assertions

class ExtensionsTest {

    private val user = User(
        id = 1,
        email = "john.doe@example.com",
        imageUrl = "https://thispersondoesnotexist.com/",
        name = "John Doe",
        website = ""
    )

    @org.junit.jupiter.api.Test
    fun `test transform UserResponse to User success`() {
        val userDTO = UserDTO(
            id = 1,
            email = "john.doe@example.com",
            name = "John Doe",
            website = ""
        )

        val result = userDTO.toUser()
        Assertions.assertEquals(user, result)
    }

    @org.junit.jupiter.api.Test
    fun `test transform UserEntity to User success`() {
        val userEntity = UserEntity(
            id = 1,
            email = "john.doe@example.com",
            imageUrl = "https://thispersondoesnotexist.com/",
            name = "John Doe",
            website = ""
        )

        val result = userEntity.toUser()
        Assertions.assertEquals(user, result)
    }

    @org.junit.jupiter.api.Test
    fun `test transform User to UserEntity success`() {
        val expected = UserEntity(
            id = 1,
            email = "john.doe@example.com",
            imageUrl = "https://thispersondoesnotexist.com/",
            name = "John Doe",
            website = ""
        )

        val result = user.toUserEntity()
        Assertions.assertEquals(expected, result)
    }
}
