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
import com.evilapp.fire.model.LogoutResponse;
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
    public ResponseEntity<LogoutResponse> logoutAndReturnMessage(@RequestHeader("Authorization") String authorizationHeader) {
        String token = extractTokenFromHeader(authorizationHeader);
        String username = jwtService.extractUsername(token);
        LogoutResponse logoutResponse = new LogoutResponse("", username);
        try {
            if (blacklistService.addToBlacklist(token)) {
                logoutResponse.setMessage("Logout successful for user.");
                return ResponseEntity.ok(logoutResponse); //TODO add extract username to add username in msg
            } else {
                logoutResponse.setMessage("Token already invalidated");
                return ResponseEntity.badRequest().body(logoutResponse);
            }
        } catch (IllegalArgumentException e) {
            logoutResponse.setMessage("Invalid token");
            return ResponseEntity.badRequest().body(logoutResponse);
        } catch (ExpiredJwtException e) {
            logoutResponse.setMessage("Token has expired");
            return ResponseEntity.badRequest().body(logoutResponse);
        } catch (Exception e) {
            logoutResponse.setMessage("Error logging out");
            return ResponseEntity.badRequest().body(logoutResponse);
        }
    }

    private String extractTokenFromHeader(String authorizationHeader) {
        return authorizationHeader.replace("Bearer ", "");
    }
}
