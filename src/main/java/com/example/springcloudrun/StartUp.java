package com.example.springcloudrun;

import com.example.springcloudrun.user.User;
import com.example.springcloudrun.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartUp implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        List<User> users = List.of(
                new User("john@email.com", "John"),
                new User("solomon@email.com", "Solomon")
        );

        userRepository.saveAll(users);
    }
}
