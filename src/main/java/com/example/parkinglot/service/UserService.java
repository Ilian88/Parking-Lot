package com.example.parkinglot.service;

import com.example.parkinglot.model.entity.UserEntity;

public interface UserService {
    UserEntity findUserByUsername(String username);
}
