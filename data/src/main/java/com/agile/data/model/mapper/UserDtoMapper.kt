package com.agile.data.model.mapper

import com.agile.data.model.AddressDto
import com.agile.data.model.CompanyDto
import com.agile.data.model.GeoDto
import com.agile.domain.model.User
import com.agile.data.model.UserDto

fun UserDto.toUser(): User {
    return User(
        id = id,
        name = name,
        username = username,
        email = email,
        phone = phone,
        website = website,
        companyName = company.name
    )
}

fun List<UserDto>.toUserList(): List<User> {
    return map { it.toUser() }
}

fun User.toUserDto(): UserDto {
    return UserDto(
        id = id,
        name = name,
        username = username,
        email = email,
        phone = phone,
        website = website,
        address = AddressDto(
            street = "",
            suite = "",
            city = "",
            zipcode = "",
            geo = GeoDto(lat = "", lng = "")
        ),
        company = CompanyDto(
            name = companyName,
            catchPhrase = "",
            bs = ""
        )
    )
}

fun List<User>.toUserDtoList(): List<UserDto> {
    return map { it.toUserDto() }
}