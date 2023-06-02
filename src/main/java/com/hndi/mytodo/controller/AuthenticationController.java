package com.hndi.mytodo.controller;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.hndi.mytodo.model.AuthenticationRequest;
import com.hndi.mytodo.model.AuthenticationResponse;
import com.hndi.mytodo.model.User;
import com.hndi.mytodo.service.JwtUtil;
import com.hndi.mytodo.service.MyUserDetailsService;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @GetMapping("/api/v1/users/{username}")
    public User getUserInfo (@PathVariable("username") String username, @RequestHeader("Authorization") String authString) throws AccessDeniedException {
        return userDetailsService.getUser(username, authString);
    }

    @PutMapping("/api/v1/users/{username}")
    public void updateUserInfo(@PathVariable("username") String username, @RequestBody User user) {
        userDetailsService.updateUser(username, user);
    }


    @PostMapping("/api/v1/auth/signup")
    public ResponseEntity<?> createUser(@RequestBody AuthenticationRequest authenticationRequest){
        userDetailsService.createUser(authenticationRequest);
        return ResponseEntity.ok(null);
    }

    @PostMapping(value={"/api/v1/auth/login", "/api/v1/token"})
    public ResponseEntity<?> createAuthenticationToken (@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }
        catch (BadCredentialsException e) {
            throw new Exception("incorrect username or password",e);
        }

        final UserDetails userDetails= userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt= jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


}
