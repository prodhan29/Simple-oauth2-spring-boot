package com.example.authdemo.Controller.rest;

import com.example.authdemo.Config.CustomConfiugration;
import com.example.authdemo.Domain.User;
import com.example.authdemo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Past;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class HomeRestController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/create/user")
    public String createUser(@RequestBody User user) {

        user.setPassword(CustomConfiugration.getPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return "created";
    }
}
