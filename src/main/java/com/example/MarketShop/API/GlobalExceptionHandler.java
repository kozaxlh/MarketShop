/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.MarketShop.API;

import com.example.MarketShop.DTO.ResponseObject;
import com.example.MarketShop.Exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Admin
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUnknownedException(Exception e) {
        e.printStackTrace();
        return "Unknown error";
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseObject> jsonNotAvailableException(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("Failed", "Format JSON not Available")
        );
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ResponseObject> handleAppException(AppException e) {
        e.printStackTrace();
        return ResponseEntity.status(e.getCode()).body(
                new ResponseObject("Failed", e.getMessege())
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseObject> handleUnAuthorization(Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ResponseObject("Failed", "You haven't permission to access")
        );
    }
}
