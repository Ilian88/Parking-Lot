package com.example.parkinglot.service.impl;

import com.example.parkinglot.model.entity.UserEntity;
import com.example.parkinglot.repository.UserRepository;
import com.example.parkinglot.service.UserService;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(()-> new NoSuchElementException("There is no such user"));
    }
}
