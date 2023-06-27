package com.example.MarketShop.Repository;

import com.example.MarketShop.Model.Orderdetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderdetailRepository extends JpaRepository<Orderdetail, Integer>, JpaSpecificationExecutor<Orderdetail> {

}