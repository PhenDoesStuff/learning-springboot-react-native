package com.montague.serverproductmanagement.service;

import com.montague.serverproductmanagement.model.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Product product);

    Long numberOfProduct();

    List<Product> findAllProducts();
}
