/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.MarketShop.Model;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Data
@Embeddable
public class OrderdetailPK implements Serializable {

    @Basic(optional = false)
    @Column(nullable = false)
    private int orderID;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private int productID;

    public OrderdetailPK() {
    }

    public OrderdetailPK(int orderID, int productID) {
        this.orderID = orderID;
        this.productID = productID;
    }
}
