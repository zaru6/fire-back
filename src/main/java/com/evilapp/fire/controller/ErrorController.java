package com.evilapp.fire.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evilapp.fire.exception.FireException;

@RestController
@RequestMapping("/api")
public class ErrorController {

    @GetMapping("/error")
    public String throwError() {
        throw new FireException("This is a FIRE error message");
    }

    @GetMapping("/generic-error")
    public String throwGenericError() {
        throw new RuntimeException("This is a generic FIRE error message");
    }
}