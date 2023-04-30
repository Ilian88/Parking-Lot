package com.example.parkinglot;

import com.example.parkinglot.model.entity.UserEntity;
import com.example.parkinglot.model.enums.Role;
import com.example.parkinglot.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DBInitialValueCreator implements CommandLineRunner {
    private final UserRepository userRepository;

    public DBInitialValueCreator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        UserEntity user = new UserEntity();

        user.setUsername("ilian88")
                .setPassword(new BCryptPasswordEncoder().encode("12345"))
                .setRole(Role.ADMIN);

        this.userRepository.save(user);
    }
}
