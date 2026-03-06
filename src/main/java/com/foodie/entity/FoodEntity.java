package com.foodie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Documented;

@Data
@Table(name = "food")
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private double price;
    private String category;
    private  String imageUrl;

}
