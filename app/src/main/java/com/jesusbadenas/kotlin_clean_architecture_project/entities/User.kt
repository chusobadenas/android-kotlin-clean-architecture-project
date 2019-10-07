package com.jesusbadenas.kotlin_clean_architecture_project.entities

/**
 * User used in the presentation layer.
 */
data class User(
    val userId: Int,
    var coverUrl: String? = null,
    var fullName: String? = null,
    var email: String? = null,
    var description: String? = null,
    var followers: Int? = 0
)
