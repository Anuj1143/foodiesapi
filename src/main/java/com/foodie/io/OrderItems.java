package com.foodie.io;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor

public class OrderItems {

    private String foodId;
    private int quantity;
    private double price;
    private String category;
    private String imageUrl;
    private String description;
    private String name;

}
