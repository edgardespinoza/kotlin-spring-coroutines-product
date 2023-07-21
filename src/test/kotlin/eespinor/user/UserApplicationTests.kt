package eespinor.user

import eespinor.user.domain.model.User
import eespinor.user.domain.repository.UserRepository
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserApplicationTests(@Autowired val userRepository: UserRepository) {

    @Test
    fun contextLoads() {
        runBlocking {
            val users = userRepository.findAll()
            userRepository.save(User(null, "test@mail.com", "test name", 30, 1));

            Assertions.assertEquals(8, users.count ())
        }
    }

}
