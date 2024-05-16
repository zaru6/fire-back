package com.evilapp.fire.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evilapp.fire.dtos.LoginUserDto;
import com.evilapp.fire.dtos.RegisterUserDto;
import com.evilapp.fire.model.LoginResponse;
import com.evilapp.fire.model.User;
import com.evilapp.fire.service.AuthenticationService;
import com.evilapp.fire.service.JwtService;

import io.jsonwebtoken.ExpiredJwtException;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        Optional<String> username = Optional.ofNullable(jwtService.extractUsername(token));
        if (username.isPresent()) {
            jwtService.addToBlacklist(token);
        }
        try {
            //TODO: validateToken(), isExpired.... 
            return ResponseEntity.ok("Logout successful for user: " + username.get());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid token");
        } catch (ExpiredJwtException e) {
            return ResponseEntity.badRequest().body("Token has expired");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error logging out");
        }
    }
}
