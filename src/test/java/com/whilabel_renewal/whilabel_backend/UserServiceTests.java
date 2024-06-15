package com.whilabel_renewal.whilabel_backend;


import com.whilabel_renewal.whilabel_backend.domain.User;
import com.whilabel_renewal.whilabel_backend.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Rollback(value = false)
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUserTest() {

        User user = new User();
        user.setNickname("hassan");

        userRepository.save(user);

        User result = userRepository.findById(user.getId()).get();

        Assertions.assertThat(user.getNickname()).isEqualTo(result.getNickname());


    }
}
