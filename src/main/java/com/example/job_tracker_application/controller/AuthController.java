package com.example.job_tracker_application.controller;

import com.example.job_tracker_application.model.JobApplication;
import com.example.job_tracker_application.model.User;
import com.example.job_tracker_application.repository.UserRepository;
import com.example.job_tracker_application.security.JwtUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user = new User(user.getName(), user.getEmail(),
                passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return  "User register successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        Optional<User> found = userRepository.findByEmail(user.getEmail());
        if (found.isPresent() &&
        passwordEncoder.matches(user.getPassword(), found.get().getPassword())) {
            return jwtUtil.generatedToken(user.getEmail());
        }
        return "Invalid credentials";
    }
}
