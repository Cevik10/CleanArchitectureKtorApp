package com.agile.data.model.mapper

import com.agile.domain.model.User
import com.agile.data.model.UserDto

fun UserDto.toUser(): User {
    return User(
        id = id,
        name = name,
        email = email,
        companyName = company.name
    )
}

fun List<UserDto>.toUserList(): List<User> {
    return map { it.toUser() }
}
