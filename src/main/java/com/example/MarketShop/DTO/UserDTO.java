/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.MarketShop.DTO;

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
public class UserDTO {
    private Integer userID;

    private String email;
    
    private String password;
    
    private String fullname;
    
    private String address;
    
    private String city;
    
    private List<String> userRoles;
    
    private List<Integer> ordersID;
}
