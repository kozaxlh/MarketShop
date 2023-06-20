/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.MarketShop.Service.Interface;

import com.example.MarketShop.Exception.AppException;
import com.example.MarketShop.DTO.ProductDTO;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Admin
 */
public interface ProductService {
    List<ProductDTO> getProductList();
    
    ProductDTO addProduct(ProductDTO product);

    ProductDTO findByID(Integer productID);

    ProductDTO updateProduct(ProductDTO newProduct);

    String deleteProduct(Integer productID);
}
