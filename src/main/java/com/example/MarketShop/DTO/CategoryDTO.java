/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.MarketShop.DTO;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class CategoryDTO implements Serializable {

    private Integer categoryID;

    @NotEmpty
    @NotNull
    private String name;

    @NotNull
    private String description;

    @JsonFilter("ProductInCategory")
    private Collection<ProductDTO> productCollection;
}
