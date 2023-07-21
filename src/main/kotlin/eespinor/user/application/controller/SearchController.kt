package eespinor.user.application.controller

import eespinor.user.application.TypeResponse
import eespinor.user.application.toResponse
import eespinor.user.application.toType
import eespinor.user.domain.model.Company
import eespinor.user.domain.model.User
import eespinor.user.domain.usecase.CompanyService
import eespinor.user.domain.usecase.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/search")
class SearchController (private val userService: UserService,
    private val companyService: CompanyService){

    @GetMapping
    suspend fun searchByName(
        @RequestParam("query") query: String
    ): Flow<TypeResponse>{
        val userFlow = userService.findByNameLike(query)
            .map (User::toType)

        val companyFlow = companyService.findByName(query)
            .map(Company::toType)

        return merge(userFlow, companyFlow)
    }
}