package com.foodie.services;

import com.foodie.entity.OrderEntity;
import com.foodie.io.OrderRequest;
import com.foodie.io.OrderResponse;
import com.foodie.repository.CartRepository;
import com.foodie.repository.OrderRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserService userService;
    @Value("${razorpay.key}")
    private String RAZORPAY_KEY;
    @Value("${razorpay.secret}")
    private String RAZORPAY_SECRET;
    @Override
    public OrderResponse createOrderWithPayment(OrderRequest request) throws RazorpayException {
       OrderEntity newOrder= convertToEntity(request);
      newOrder= orderRepository.save(newOrder);


      //create razor pay payment order
        RazorpayClient razorpayClient=new RazorpayClient(RAZORPAY_KEY, RAZORPAY_SECRET);
        JSONObject orderRequest=new JSONObject();
        orderRequest.put("amount", newOrder.getAmount());
        orderRequest.put("currency", "INR");
        orderRequest.put("payment_capture", 1);
       Order razorpayOrder= razorpayClient.orders.create(orderRequest);
       newOrder.setRazorpayOrderId(razorpayOrder.get("id"));

      String loggedInUserId= userService.findByUserId();
      newOrder.setUserId(loggedInUserId);
     newOrder= orderRepository.save(newOrder);
     return convertToResponse(newOrder);

    }

    @Override
    @Transactional
    public void verifyPayment(Map<String, String> paymentData, String status) {
       String razorpayOrderId= paymentData.get("razorpay_order_id");
      OrderEntity existingOrder= orderRepository.findByRazorpayOrderId(razorpayOrderId).orElseThrow(()->new RuntimeException("Order Not Found"));
      existingOrder.setPaymentStatus(status);
      existingOrder.setRazorpaySignature(paymentData.get("razorpay_signature"));
      existingOrder.setRazorpayPaymentId(paymentData.get("razorpay_payment_id"));
      orderRepository.save(existingOrder);
      if("paid".equalsIgnoreCase(status)){
            cartRepository.deleteByUserId(existingOrder.getUserId());

      }
    }

    @Override
    public List<OrderResponse> getUserOrders() {
        String loggedInUserId=userService.findByUserId();
        List<OrderEntity> list= orderRepository.findByUserId(loggedInUserId);
        return list.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    @Override
    public void removeOrder(String orderId) {
        orderRepository.deleteById(orderId);

    }

    @Override
    public List<OrderResponse> getOrdersOfAllUsers() {
      List<OrderEntity> list=  orderRepository.findAll();
     return list.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    @Override
    public void updateOrderStatus(String orderId, String status) {
        OrderEntity entity= orderRepository.findById(orderId).orElseThrow(()-> new RuntimeException("Order not found"));
        entity.setOrderStatus(status);
        orderRepository.save(entity);

    }


    private OrderResponse convertToResponse(OrderEntity newOrder) {
       return  OrderResponse.builder()
                .id(newOrder.getId())
                .amount(newOrder.getAmount())
                .userAddress(newOrder.getUserAddress())
                .userId(newOrder.getUserId())
                .razorpayOrderId(newOrder.getRazorpayOrderId())
                .paymentStatus(newOrder.getPaymentStatus())
                .orderStatus(newOrder.getOrderStatus())
                .email(newOrder.getEmail())
                .orderedItems(newOrder.getOrderedItems())
                .phoneNumber(newOrder.getPhoneNumber())
                .build();
    }

    private OrderEntity convertToEntity(OrderRequest request) {
       return OrderEntity.builder()

                .userAddress(request.getUserAddress())
                .amount(request.getAmount())
                .orderedItems(request.getOrderedItems())
               .email(request.getEmail())
               .phoneNumber(request.getPhoneNumber())
               .orderStatus(request.getOrderStatus())
                .build();
    }
}
