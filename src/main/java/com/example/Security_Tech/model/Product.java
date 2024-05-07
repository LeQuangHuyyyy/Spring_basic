package com.example.Security_Tech.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String Brand;
    private String category;
    @Column(columnDefinition = "Text")
    private String description;
    private Date CreateDate;
    private String imageUrl;
}
