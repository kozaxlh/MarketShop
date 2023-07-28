package com.example.MarketShop.Repository;

import com.example.MarketShop.Model.Orders;
import com.example.MarketShop.Model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface OrdersRepository extends JpaRepository<Orders, Long>, JpaSpecificationExecutor<Orders> {
    Page<Orders> findByCustomer(Pageable pageable, Users user);

    @Query(value = """
            SELECT `AUTO_INCREMENT`
            FROM  INFORMATION_SCHEMA.TABLES
            WHERE TABLE_SCHEMA = 'marketshop'
            AND   TABLE_NAME   = 'orders'
            """, nativeQuery = true)
    Integer getAutoIncrementID();
}