package org.scalke.userservice.web.controllers;

import jakarta.validation.Valid;
import org.scalke.userservice.web.requests.*;
import org.scalke.userservice.web.responses.ChangePasswordResponse;
import org.scalke.userservice.web.responses.ForgotPasswordResponse;
import org.scalke.userservice.web.responses.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
public interface AuthController {
    @PostMapping(path = "/signup")
    ResponseEntity<String> signup(@Valid @RequestBody SignupRequest request);

    @PostMapping(path = "/login")
    ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) throws MethodArgumentNotValidException;

    @GetMapping("/check-token")
    ResponseEntity<String> checkToken() ;

    @PatchMapping(path = "/change-password")
    ResponseEntity<ChangePasswordResponse> changePassword(@Valid @RequestBody ChangePasswordRequest request);

    @PostMapping(path = "/create-reset-password-code")
    ResponseEntity<String> createResetPasswordCode(@RequestBody ResetPasswordRequest request);

    @PatchMapping(path = "/forgot-password")
    ResponseEntity<ForgotPasswordResponse> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request);


}
