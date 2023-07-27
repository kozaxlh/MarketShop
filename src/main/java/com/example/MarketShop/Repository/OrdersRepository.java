package com.example.MarketShop.Repository;

import com.example.MarketShop.Model.Orders;
import com.example.MarketShop.Model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long>, JpaSpecificationExecutor<Orders> {
    Page<Orders> findByCustomer(Pageable pageable, Users user);

    @Query(value = "SELECT max(O.orderID) FROM Orders O", nativeQuery = true)
    Integer getLastInsertedID();
}