package com.evilapp.fire.controller;

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
import com.evilapp.fire.service.BlackListService;
import com.evilapp.fire.service.JwtService;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final BlackListService blacklistService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, BlackListService blacklistService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.blacklistService = blacklistService;
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
    public ResponseEntity<String> logoutAndReturnMessage(@RequestHeader("Authorization") String authorizationHeader) {
        System.out.println("logout started");
        try {
            String token = extractTokenFromHeader(authorizationHeader);
            if (blacklistService.addToBlacklist(token)) {
                return ResponseEntity.ok("Logout successful for user: " + jwtService.extractUsername(token));
            } else {
                return ResponseEntity.badRequest().body("Token already invalidated");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid token");
        } catch (ExpiredJwtException e) {
            return ResponseEntity.badRequest().body("Token has expired");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error logging out");
        }
    }

    private String extractTokenFromHeader(String authorizationHeader) {
        return authorizationHeader.replace("Bearer ", "");
    }
}
