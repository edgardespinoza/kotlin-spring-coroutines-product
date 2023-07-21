package eespinor.user.application

import eespinor.user.domain.model.Company
import eespinor.user.domain.model.User

data class UserResponse(
    val id: Long,
    val email: String,
    val name: String,
    val age: Int,
)

data class CompanyResponse(
    val id: Long,
    val name: String,
    val address: String,
    val users: List<UserResponse>?
)

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id ?: 0,
        email = email,
        name = name,
        age = age
    )
}

fun Company.toResponse(users: List<User>?= emptyList()): CompanyResponse {
    return CompanyResponse(
        id = id ?: 0,
        name = name,
        address = address,
        users = users?.map(User::toResponse)
    )
}