package com.montague.serverproductmanagement.controller;

import com.montague.serverproductmanagement.jwt.JwtTokenProvider;
import com.montague.serverproductmanagement.model.Role;
import com.montague.serverproductmanagement.model.Transaction;
import com.montague.serverproductmanagement.model.User;
import com.montague.serverproductmanagement.service.ProductService;
import com.montague.serverproductmanagement.service.TransactionService;
import com.montague.serverproductmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
public class UserController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("api/user/registration")
    public ResponseEntity<User> register(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            user.setRole(Role.USER);
            return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
        }
    }

    @GetMapping("api/user/login")
    public ResponseEntity<?> getUser(Principal principal) {
        if (principal == null || principal.getName() == null) {
            return ResponseEntity.ok(principal);
        }
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        User user = userService.findByUsername(authenticationToken.getName());
        user.setToken(jwtTokenProvider.generateToken(authenticationToken));

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PostMapping("api/user/purchase")
    public ResponseEntity<Transaction> purchaseProduct(@RequestBody Transaction transaction) {
        transaction.setPurchaseDate(LocalDateTime.now());
        return new ResponseEntity<>(transactionService.saveTransaction(transaction), HttpStatus.CREATED);
    }

    @GetMapping("api/user/products")
    public ResponseEntity<?> getAllProducts() {
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }
}
