/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.MarketShop.Repository;

import com.example.MarketShop.Model.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Admin
 */
public interface ProductRepository extends GenericRepository<Product, Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Product e SET e.delete_at = NOW() WHERE e.productID= ?1", nativeQuery = true)
    void delete(String productID);

    Optional<Product> findByProductName(String productName);
}
