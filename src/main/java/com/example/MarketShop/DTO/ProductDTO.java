package com.example.MarketShop.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {
    
    private Integer productID;
    
    @NotNull
    @NotEmpty
    private String productName;
    
    @NotNull
    private String image;
    
    @NotNull
    private Integer categoryID;
    
    @NotNull
    @NotEmpty
    private String unit;
    
    @NotNull
    private Integer amount;
    
    @NotNull
    private Float price;
}
