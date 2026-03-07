package com.foodie.services;

import com.foodie.io.CartRequest;
import com.foodie.io.CartResponse;

public interface CartService {

   CartResponse addToCart(CartRequest request);

   CartResponse getCart();

   void clearCart();

  CartResponse removeFromCart(CartRequest cartRequest);
}
