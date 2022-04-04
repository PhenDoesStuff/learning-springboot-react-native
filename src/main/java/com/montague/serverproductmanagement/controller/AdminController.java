package com.montague.serverproductmanagement.controller;

import com.montague.serverproductmanagement.model.Product;
import com.montague.serverproductmanagement.model.StringResponse;
import com.montague.serverproductmanagement.model.Transaction;
import com.montague.serverproductmanagement.model.User;
import com.montague.serverproductmanagement.service.ProductService;
import com.montague.serverproductmanagement.service.TransactionService;
import com.montague.serverproductmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ProductService productService;

    @PutMapping("api/admin/user-update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User existUser = userService.findByUsername(user.getUsername());

        if (existUser != null && !existUser.getId().equals(user.getId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.CREATED);
    }

    @PostMapping("api/admin/user-delete")
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("api/admin/user-all")
    public ResponseEntity<List<User>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("api/admin/user-number")
    public ResponseEntity<StringResponse> numberOfProducts() {
        Long number = userService.numberOfUsers();
        StringResponse response = new StringResponse();
        response.setResponse(number.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("api/admin/product-create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("api/admin/product-update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.CREATED);
    }

    @PostMapping("api/admin/product-delete")
    public ResponseEntity<String> deleteProduct(@RequestBody Product product) {
        productService.deleteProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("api/admin/product-all")
    public ResponseEntity<List<Product>> findAllProducts() {
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    @GetMapping("api/admin/product-number")
    public ResponseEntity<StringResponse> getNumberOfProducts() {
        Long numOfProducts = productService.numberOfProduct();
        StringResponse response = new StringResponse();
        response.setResponse(numOfProducts.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("api/admin/transaction-all")
    public ResponseEntity<List<Transaction>> findAllTransactions() {
        return new ResponseEntity<>(transactionService.findAllTransactions(), HttpStatus.OK);
    }

    @GetMapping("api/admin/transaction-number")
    public ResponseEntity<StringResponse> getNumberOfTransactions() {
        Long num = transactionService.numberOfTransactions();
        StringResponse response = new StringResponse();
        response.setResponse(num.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
