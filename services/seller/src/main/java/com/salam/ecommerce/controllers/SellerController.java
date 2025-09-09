package com.salam.ecommerce.controllers;

import com.salam.ecommerce.dto.RegisterReponse;
import com.salam.ecommerce.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping("/register")
    public ResponseEntity<RegisterReponse> register(@RequestParam("inn") String inn, @RequestParam("email") String email) {
        RegisterReponse registerReponse = sellerService.register(inn, email);
        return ResponseEntity.ok(registerReponse);
    }
}
