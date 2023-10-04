package com.example.blog.springbootblogrestapi.security;

import com.example.blog.springbootblogrestapi.entity.User;
import com.example.blog.springbootblogrestapi.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;



    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository=userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

       User user = userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
               .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username or email: " + usernameOrEmail));

        Set<GrantedAuthority> grantedAuthoritySet = user.getRoles().
                stream().
                map((roles) -> new SimpleGrantedAuthority(roles.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                grantedAuthoritySet);
    }
}
