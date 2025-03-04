package com.jesusbadenas.kotlin_clean_architecture_project.domain.usecase

import com.jesusbadenas.kotlin_clean_architecture_project.domain.model.User
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repository.UserRepository

class GetUserUseCase(
    private val userRepository: UserRepository
) : UseCase<GetUserUseCase.Params, User?>() {

    override suspend fun execute(params: Params): User? = userRepository.getUser(params.userId)

    data class Params(
        val userId: Int
    )
}
