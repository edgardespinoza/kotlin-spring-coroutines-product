package eespinor.user.application

import com.fasterxml.jackson.annotation.JsonProperty
import eespinor.user.domain.model.Company
import eespinor.user.domain.model.User

data class UserRequest (
    val email: String,
    val name:String,
    val age: Int,
    @JsonProperty("company_id")
    val companyId: Long
)

fun UserRequest.toModel() :User{
    return User(
        email = email,
        name = name,
        age = age,
        companyId = companyId
    )
}

data class CompanyRequest(
    val name : String,
    val address: String
)

fun CompanyRequest.toModel(): Company {
    return Company(
        name = name,
        address = address
    )
}