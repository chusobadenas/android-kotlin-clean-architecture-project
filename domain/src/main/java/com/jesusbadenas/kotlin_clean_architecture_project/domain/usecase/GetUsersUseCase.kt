package com.jesusbadenas.kotlin_clean_architecture_project.domain.usecase

import com.jesusbadenas.kotlin_clean_architecture_project.domain.model.User
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repository.UserRepository

class GetUsersUseCase(
    private val userRepository: UserRepository
) : UseCaseNoParams<List<User>>() {

    override suspend fun execute(): List<User> = userRepository.getUsers()
}
