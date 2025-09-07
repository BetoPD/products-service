package com.manufacturas.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manufacturas.product.config.ServiceConfig;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ServiceConfig serviceConfig;

    @GetMapping
    public ResponseEntity<String> getAllProducts() {

        return ResponseEntity.ok("List of products" + serviceConfig.getProperty());
    }
}
