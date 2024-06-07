package com.example.blogging_platform.service;

import com.example.blogging_platform.dto.AuthenticationRequest;
import com.example.blogging_platform.dto.AuthenticationResponse;
import com.example.blogging_platform.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
