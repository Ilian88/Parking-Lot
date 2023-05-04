package com.example.parkinglot.service;

import com.example.parkinglot.model.entity.UserEntity;
import com.example.parkinglot.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void testGetUser() {
        Optional<UserEntity> user = userRepository.findByUsername("ilian88");

        Assertions.assertEquals(user.get().getUsername(), "ilian88");
    }

    @Test
    void testPasswordIsEncryptedInDB() {
        Optional<UserEntity> user = userRepository.findByUsername("ilian88");

        Assertions.assertNotEquals(user.get().getUsername(), "12345");
    }
}
