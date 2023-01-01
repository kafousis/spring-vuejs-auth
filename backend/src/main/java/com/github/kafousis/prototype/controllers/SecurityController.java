package com.github.kafousis.prototype.controllers;

import com.github.kafousis.prototype.entities.User;
import com.github.kafousis.prototype.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
public class SecurityController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "api/me", method = RequestMethod.GET)
    public ResponseEntity<?> currentUser(Principal principal) {

        String username = principal.getName();
        Optional<User> userByUsername = userRepository.findByUsername(username);

        return userByUsername.isPresent()
                ? new ResponseEntity<>(userByUsername.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
