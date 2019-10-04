package com.jesusbadenas.kotlin_clean_architecture_project.domain.entities

/**
 * User used in the domain layer.
 */
data class UserEntity(
    val userId: Int,
    var coverUrl: String? = null,
    var fullName: String? = null,
    var email: String? = null,
    var description: String? = null,
    var followers: Int = 0
)
