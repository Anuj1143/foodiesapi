package com.foodie.services;

import com.foodie.io.UserRequest;
import com.foodie.io.UserResponse;

public interface UserService {
   UserResponse registerUser(UserRequest request);
  String findByUserId();
}
