package com.example.blog.springbootblogrestapi.controller;

import com.example.blog.springbootblogrestapi.exception.BlogAPIException;
import com.example.blog.springbootblogrestapi.payloadDtos.LoginDtos;
import com.example.blog.springbootblogrestapi.payloadDtos.RegisterDto;
import com.example.blog.springbootblogrestapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthContoller {

    private AuthService authService;

    public AuthContoller(AuthService authService){
        this.authService=authService;
    }

    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<String> login(@RequestBody LoginDtos loginDtos) {

        String response = authService.login(loginDtos);

        return ResponseEntity.ok(response);

    }

    @PostMapping(value ={ "/register","/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) throws BlogAPIException {
       String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
