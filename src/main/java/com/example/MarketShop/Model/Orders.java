/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.MarketShop.Model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Admin
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Orders")
@NamedQueries({
        @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
        @NamedQuery(name = "Orders.findByOrderID", query = "SELECT o FROM Orders o WHERE o.orderID = :orderID"),
        @NamedQuery(name = "Orders.findByDate", query = "SELECT o FROM Orders o WHERE o.date = :date"),
        @NamedQuery(name = "Orders.findByTotal", query = "SELECT o FROM Orders o WHERE o.total = :total")})
public class Orders implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "orderID", nullable = false)
    private Integer orderID;

    @Basic(optional = false)
    @Column(insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    @Basic(optional = false)
    @Column(nullable = false)
    private float total;

    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String note;

    @Basic(optional = false)
    @Column(insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime update_at;

    @Basic(optional = false)
    @Column
    private LocalDateTime delete_at;

    @JoinColumn(name = "CustomerID", referencedColumnName = "userID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Users customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders", fetch = FetchType.LAZY)
    private Collection<Orderdetail> productList;

}
