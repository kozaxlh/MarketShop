package com.example.MarketShop.Service.Implement;

import com.example.MarketShop.Repository.OrderdetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderdetailServiceImpl implements com.example.MarketShop.Service.Interface.OrderdetailService {

    @Autowired
    private OrderdetailRepository orderdetailRepository;
}
