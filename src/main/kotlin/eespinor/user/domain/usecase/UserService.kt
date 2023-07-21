package eespinor.user.domain.usecase

import eespinor.user.domain.model.User
import eespinor.user.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(private val userRepository: UserRepository) {

    suspend fun saveUser(user:User): User? =
        userRepository.byEmail(user.email)
            .firstOrNull()
            ?.let { throw ResponseStatusException(HttpStatus.BAD_REQUEST) }
            ?: userRepository.save(user)

    suspend fun findAllUser() : Flow<User> =
        userRepository.findAll()

    suspend fun findById(id: Long): User? =
        userRepository.findById(id)

    suspend fun deleteById(id:Long){
        userRepository.findById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

        userRepository.deleteById(id)
    }

    suspend fun updateById(id: Long, requestUser: User):User{
        val foundUser = userRepository.findById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

        return userRepository.save(requestUser.copy(id=foundUser.id))
    }

    suspend fun findByCompanyId(companyId:Long):Flow<User> =
        userRepository.findByCompanyId(companyId)

    suspend fun findByNameLike(name:String):Flow<User> =
        userRepository.findByNameContaining(name)
}