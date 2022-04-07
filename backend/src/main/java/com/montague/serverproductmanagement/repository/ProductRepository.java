package com.montague.serverproductmanagement.repository;

import com.montague.serverproductmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
