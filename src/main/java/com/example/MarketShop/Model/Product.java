package com.example.MarketShop.Model;

import java.io.Serial;
import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.*;

/**
 *
 * @author Admin
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByProductID", query = "SELECT p FROM Product p WHERE p.productID = :productID"),
    @NamedQuery(name = "Product.findByProductName", query = "SELECT p FROM Product p WHERE p.productName = :productName"),
    @NamedQuery(name = "Product.findByUnit", query = "SELECT p FROM Product p WHERE p.unit = :unit"),
    @NamedQuery(name = "Product.findByAmount", query = "SELECT p FROM Product p WHERE p.amount = :amount"),
    @NamedQuery(name = "Product.findByImage", query = "SELECT p FROM Product p WHERE p.image = :image"),
    @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price")})
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer productID;
    
    @Basic(optional = false)
    @Column(name = "product_name")
    private String productName;
    @Basic(optional = false)
    @Column(nullable = false)
    private String unit;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private int amount;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private String image;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private float price;
    
    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Category category;
}
