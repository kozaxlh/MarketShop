package com.example.MarketShop.Repository;

import com.example.MarketShop.Model.Orders;
import com.example.MarketShop.Model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrdersRepository extends GenericRepository<Orders, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Orders e SET e.delete_at = NOW() WHERE e.orderID= ?1", nativeQuery = true)
    void delete(String orderID);

    Page<Orders> findByCustomer(Pageable pageable, Users user);

    @Query(value = """
            SELECT `AUTO_INCREMENT`
            FROM  INFORMATION_SCHEMA.TABLES
            WHERE TABLE_SCHEMA = 'marketshop'
            AND   TABLE_NAME   = 'orders'
            """, nativeQuery = true)
    Integer getAutoIncrementID();
}