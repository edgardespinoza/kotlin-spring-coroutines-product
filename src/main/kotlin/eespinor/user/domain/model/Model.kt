package eespinor.user.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("application.company")
data class Company(
    @Id val id: Long? = null,
    val name: String,
    val address: String
)

@Table("application.app_user")
data class User(
    @Id val id: Long?=null,
    val email: String,
    val name: String,
    val age: Int,
    val companyId: Long
)
