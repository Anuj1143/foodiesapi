package com.foodie.services;

import com.foodie.entity.UserEntity;
import com.foodie.io.UserRequest;
import com.foodie.io.UserResponse;
import com.foodie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFacade authenticationFacade;
    @Override
    public UserResponse registerUser(UserRequest request) {
      UserEntity newUser=  convertToEntity(request);
     newUser= userRepository.save(newUser);
    return  convertToResponse(newUser);
    }

    @Override
    public String findByUserId() {
      String loggedInUserEmail=  authenticationFacade.getAuthentication().getName();
      UserEntity loggedInUser= userRepository.findByEmail(loggedInUserEmail).orElseThrow(()-> new UsernameNotFoundException("User Not Found..."));
      return loggedInUser.getId();
    }


    private UserEntity convertToEntity(UserRequest request){
       return UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .build();
    }
    private UserResponse convertToResponse(UserEntity registerdUser){
           return UserResponse.builder()
                    .id(registerdUser.getId())
                    .name(registerdUser.getName())
                    .email(registerdUser.getEmail())
                    .build();
    }
}
