package com.agile.domain.usecase

import com.agile.domain.model.User
import com.agile.domain.repository.UserRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(id: Int): User {
        return repository.getUserById(id)
    }
}