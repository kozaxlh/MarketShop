package com.example.MarketShop.API;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class HomeController {
    public ResponseEntity home() {
        return ResponseEntity.status(HttpStatus.OK).body("Welcome to Market Shop");
    }
}
