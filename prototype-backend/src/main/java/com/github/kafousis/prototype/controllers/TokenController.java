package com.github.kafousis.prototype.controllers;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @GetMapping("/csrf/token")
    public CsrfToken csrf(CsrfToken token){
        return token;
    }
}
