package eespinor.user.application

import eespinor.user.domain.model.Company
import eespinor.user.domain.model.User

data class TypeResponse(
    val id: Long?,
    val name: String,
    val type: ResultType
)

enum class ResultType {
    USER, COMPANY,
}

fun User.toType(): TypeResponse {
    return TypeResponse(
        id = id,
        name = name,
        type = ResultType.USER
    )
}

fun Company.toType(): TypeResponse {
    return TypeResponse(
        id = id ,
        name = name,
        type = ResultType.COMPANY
    )
}