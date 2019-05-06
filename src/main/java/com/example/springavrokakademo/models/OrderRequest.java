package com.example.springavrokakademo.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    
    String orderId;
    
    String customerId;
    
    String supplierId;
    
    String firstName;
    
    String lastName;
    
    int items;
    
    Float price;
    
    Float weight;
    
    Boolean automatedEmail;
    
}
