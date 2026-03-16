package com.foodie.repository;

import com.foodie.entity.OrderEntity;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {

  List<OrderEntity> findByUserId(String userId);
  Optional<OrderEntity> findByRazorpayOrderId(String RazorpayOrderId);
}
