package eespinor.user.application.controller

import eespinor.user.application.UserRequest
import eespinor.user.application.UserResponse
import eespinor.user.application.toModel
import eespinor.user.application.toResponse
import eespinor.user.domain.model.User
import eespinor.user.domain.usecase.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.Mapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("api/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    suspend fun createUser(@RequestBody userRequest: UserRequest): UserResponse =
        userService.saveUser(user = userRequest.toModel())
            ?.toResponse()
            ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)


    @GetMapping
    suspend fun findAll(@RequestParam("name", required = false) name: String?): Flow<UserResponse> {
        val users = name?.let { userService.findByNameLike(name) } ?: userService.findAllUser()

        return users.map(User::toResponse)
    }

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: Long): UserResponse =
        userService.findById(id)
            ?.let(User::toResponse)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @DeleteMapping("/{id}")
    suspend fun deleteById(@PathVariable id: Long) =
        userService.deleteById(id)

    @PutMapping("/{id}")
    suspend fun updateById(@PathVariable id: Long, @RequestBody userRequest: UserRequest): UserResponse =
        userService.updateById(id, userRequest.toModel()).toResponse()

}


