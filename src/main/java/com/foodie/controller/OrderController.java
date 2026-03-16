package com.foodie.controller;

import com.foodie.io.OrderRequest;
import com.foodie.io.OrderResponse;
import com.foodie.services.OrderService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrderWithPayment(@RequestBody OrderRequest request) throws RazorpayException {

        return orderService.createOrderWithPayment(request);
    }
    @PostMapping("/verify")
    public void verifyPayment(@RequestBody Map<String , String> paymentData){
        orderService.verifyPayment(paymentData, "Paid");

    }
    @GetMapping
    public List<OrderResponse> getOrders(){
       return orderService.getUserOrders();
    }
    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable String orderId){
        orderService.removeOrder(orderId);
    }

    //admin panel
    @GetMapping("/all")
    public List<OrderResponse> getOrdersOfAllUsers(){
        return orderService.getOrdersOfAllUsers();
    }
    //admin panel
    @PatchMapping("/status/{orderId}")
    public void updateOrderStatus(@PathVariable String orderId , @RequestParam String status){
        orderService.updateOrderStatus(orderId, status);
    }

}
