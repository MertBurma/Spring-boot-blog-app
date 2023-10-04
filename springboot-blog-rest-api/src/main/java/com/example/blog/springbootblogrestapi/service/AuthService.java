package com.example.blog.springbootblogrestapi.service;

import com.example.blog.springbootblogrestapi.exception.BlogAPIException;
import com.example.blog.springbootblogrestapi.payloadDtos.LoginDtos;
import com.example.blog.springbootblogrestapi.payloadDtos.RegisterDto;

public interface AuthService {

    String login(LoginDtos loginDtos);

    String register(RegisterDto registerDto) throws BlogAPIException;

}
