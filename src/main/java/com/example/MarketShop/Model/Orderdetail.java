/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.MarketShop.Model;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Admin
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orderdetail")
@NamedQueries({
        @NamedQuery(name = "Orderdetail.findAll", query = "SELECT o FROM Orderdetail o"),
        @NamedQuery(name = "Orderdetail.findByOrderID", query = "SELECT o FROM Orderdetail o WHERE o.orderdetailPK.orderID = :orderID"),
        @NamedQuery(name = "Orderdetail.findByProductID", query = "SELECT o FROM Orderdetail o WHERE o.orderdetailPK.productID = :productID"),
        @NamedQuery(name = "Orderdetail.findByQuantity", query = "SELECT o FROM Orderdetail o WHERE o.quantity = :quantity"),
        @NamedQuery(name = "Orderdetail.findByPrice", query = "SELECT o FROM Orderdetail o WHERE o.price = :price")})
public class Orderdetail implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected OrderdetailPK orderdetailPK;

    @Basic(optional = false)
    @Column(nullable = false)
    private short quantity;

    @Basic(optional = false)
    @Column(nullable = false)
    private float price;

    @JoinColumn(name = "orderID", referencedColumnName = "orderID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Orders orders;

    @JoinColumn(name = "productID", referencedColumnName = "productID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Product product;

}
