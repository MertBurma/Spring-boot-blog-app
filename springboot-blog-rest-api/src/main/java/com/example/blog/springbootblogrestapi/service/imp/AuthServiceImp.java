package com.example.blog.springbootblogrestapi.service.imp;

import com.example.blog.springbootblogrestapi.entity.Roles;
import com.example.blog.springbootblogrestapi.entity.User;
import com.example.blog.springbootblogrestapi.exception.BlogAPIException;
import com.example.blog.springbootblogrestapi.payloadDtos.LoginDtos;
import com.example.blog.springbootblogrestapi.payloadDtos.RegisterDto;
import com.example.blog.springbootblogrestapi.repository.RoleRepository;
import com.example.blog.springbootblogrestapi.repository.UserRepository;
import com.example.blog.springbootblogrestapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImp implements AuthService {



    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImp(AuthenticationManager authenticationManager,
                            UserRepository userRepository,
                            RoleRepository roleRepository,
                            PasswordEncoder passwordEncoder) {

        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.roleRepository=roleRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String login(LoginDtos loginDtos) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDtos.getUsernameOrEmail(),loginDtos.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return " User Logged in Succesfully";




    }

    @Override
    public String register(RegisterDto registerDto) throws BlogAPIException {

        //check username in database

        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Username is Already exist");
        }

        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Email is Already exist");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));


        Set<Roles> roles = new HashSet<>();
        Roles userRole = roleRepository.findByName("ROLE_USER").get();

        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
        return "User Registered Succesfully";

    }
}
