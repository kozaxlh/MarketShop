/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.MarketShop.DTO;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Admin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO implements Serializable {
    private Integer userID;

    private String email;

    private String fullname;
    
    private String address;
    
    private String city;
    
    private List usersRole;
    
    private List<OrdersDTO> orders;
}