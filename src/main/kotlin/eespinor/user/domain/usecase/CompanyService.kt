package eespinor.user.domain.usecase

import eespinor.user.domain.model.Company
import eespinor.user.domain.repository.CompanyRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CompanyService(private val companyRepository: CompanyRepository) {

    suspend fun saveCompany(company:Company):Company?=
        companyRepository.save(company)

    suspend fun findAll():Flow<Company> =
        companyRepository.findAll()

    suspend fun findById(id:Long): Company? = companyRepository.findById(id)

    suspend fun findByName(name:String): Flow<Company> =
        companyRepository.findByNameContaining(name)

    suspend fun deleteById(id: Long) {
        val foundCompany = companyRepository.findById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

        companyRepository.deleteById(id)
    }

    suspend fun updateCompanyById(id:Long, companyRequest: Company):Company{
        val foundCompany = companyRepository.findById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

        return companyRepository.save(companyRequest.copy(id=foundCompany.id))
    }
}