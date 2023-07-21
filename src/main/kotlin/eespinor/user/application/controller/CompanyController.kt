package eespinor.user.application.controller

import eespinor.user.application.CompanyRequest
import eespinor.user.application.CompanyResponse
import eespinor.user.application.toModel
import eespinor.user.application.toResponse
import eespinor.user.domain.model.Company
import eespinor.user.domain.model.User
import eespinor.user.domain.usecase.CompanyService
import eespinor.user.domain.usecase.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/company")
class CompanyController(
    private val companyService: CompanyService,
    private val userService: UserService
) {

    @PostMapping
    suspend fun createCompany(@RequestBody companyRequest: CompanyRequest): CompanyResponse =
        companyService.saveCompany(companyRequest.toModel())
            ?.toResponse()
            ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)

    @GetMapping
    suspend fun findCompany(
        @RequestParam("name", required = false) name: String?
    ): Flow<CompanyResponse> {

        val companies = name?.let { companyService.findByName(it) } ?: companyService.findAll()

        return companies.map { company ->
            company.toResponse(
                users = findCompanyUsers(company)
            )
        }
    }

    private suspend fun findCompanyUsers(company: Company): List<User>? =
        company.id?.let { userService.findByCompanyId(it).toList() }


    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: Long): CompanyResponse =
        companyService.findById(id)?.let { company ->
            company.toResponse(
                users = findCompanyUsers(company)
            )
        } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @DeleteMapping("/{id}")
    suspend fun deleteById(@PathVariable id: Long) = companyService.deleteById(id)

    @PutMapping("/{id}")
    suspend fun updateById(@PathVariable id: Long, @RequestBody companyRequest: CompanyRequest): CompanyResponse =
        companyService.updateCompanyById(id, companyRequest.toModel())
            .let { company ->  company.toResponse(
                findCompanyUsers(company)
            )}


}

