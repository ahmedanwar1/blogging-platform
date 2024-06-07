package com.example.blogging_platform.service;

import com.example.blogging_platform.dto.AuthenticationRequest;
import com.example.blogging_platform.dto.AuthenticationResponse;
import com.example.blogging_platform.dto.RegisterRequest;
import com.example.blogging_platform.exception.InvalidAuthenticationCredentialsException;
import com.example.blogging_platform.exception.UserAlreadyExistsException;
import com.example.blogging_platform.util.JwtUtils;
import com.example.blogging_platform.enums.Role;
import com.example.blogging_platform.model.User;
import com.example.blogging_platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        //check user doesn't exist
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new UserAlreadyExistsException("This email is already registered.");
        }

        //register user
        var user = User.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        //generate token
        var jwtToken = jwtUtils.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        } catch (Exception e) {
            throw new InvalidAuthenticationCredentialsException("Invalid email or password.");
        }

        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        var jwtToken = jwtUtils.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
