package com.example.Security_Tech.repository;

import com.example.Security_Tech.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
