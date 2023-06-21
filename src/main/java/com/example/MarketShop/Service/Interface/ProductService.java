/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.MarketShop.Service.Interface;

import com.example.MarketShop.DTO.ProductDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface ProductService {
    ProductDTO addProduct(ProductDTO product);

    List<ProductDTO> getProductList(Pageable pageable);

    ProductDTO findByID(Integer productID);

    ProductDTO updateProduct(ProductDTO newProduct);

    String deleteProduct(Integer productID);

    int totalPage();
}
