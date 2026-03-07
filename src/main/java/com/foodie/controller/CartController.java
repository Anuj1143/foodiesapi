package com.foodie.controller;

import com.foodie.io.CartRequest;
import com.foodie.io.CartResponse;
import com.foodie.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    @PostMapping
    public CartResponse addToCart(@RequestBody CartRequest request){
       String foodId= request.getFoodId();
       if(foodId==null || foodId.isEmpty()){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Food Id not Found");
       }
       return cartService.addToCart(request);

    }

    @GetMapping()
    public CartResponse getCart(){
      return cartService.getCart();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearCart(){
        cartService.clearCart();
    }
    @PostMapping("/remove")
    public CartResponse removeFromCart(@RequestBody CartRequest request){
        String foodId=request.getFoodId();
        if(foodId ==null || foodId.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Food not Found");
        }
       return cartService.removeFromCart(request);
    }
}
