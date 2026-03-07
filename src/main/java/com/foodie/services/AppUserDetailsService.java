package com.foodie.services;

import com.foodie.entity.UserEntity;
import com.foodie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
     UserEntity user= userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
   return  new User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }
}
