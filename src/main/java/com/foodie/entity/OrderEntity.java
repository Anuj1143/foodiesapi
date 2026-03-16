package com.foodie.entity;

import com.foodie.io.OrderItems;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String userId;
    private String userAddress;
    private String phoneNumber;
    private String email;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItems> orderedItems;
    private double amount;
    private String paymentStatus;
    private String razorpayOrderId;
    private String razorpaySignature;
    private String orderStatus;
    private String razorpayPaymentId;


}
