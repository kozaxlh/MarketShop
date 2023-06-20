/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.MarketShop.Model;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Entity
@Data
@Table(catalog = "marketshop", schema = "")
@NamedQueries({
    @NamedQuery(name = "Orderdetail.findAll", query = "SELECT o FROM Orderdetail o"),
    @NamedQuery(name = "Orderdetail.findByOrderID", query = "SELECT o FROM Orderdetail o WHERE o.orderdetailPK.orderID = :orderID"),
    @NamedQuery(name = "Orderdetail.findByProductID", query = "SELECT o FROM Orderdetail o WHERE o.orderdetailPK.productID = :productID"),
    @NamedQuery(name = "Orderdetail.findByQuantity", query = "SELECT o FROM Orderdetail o WHERE o.quantity = :quantity"),
    @NamedQuery(name = "Orderdetail.findByPrice", query = "SELECT o FROM Orderdetail o WHERE o.price = :price")})
public class Orderdetail implements Serializable {

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

    public Orderdetail() {
    }

    public Orderdetail(OrderdetailPK orderdetailPK) {
        this.orderdetailPK = orderdetailPK;
    }

    public Orderdetail(OrderdetailPK orderdetailPK, short quantity, float price) {
        this.orderdetailPK = orderdetailPK;
        this.quantity = quantity;
        this.price = price;
    }

    public Orderdetail(int orderID, int productID) {
        this.orderdetailPK = new OrderdetailPK(orderID, productID);
    }
}
