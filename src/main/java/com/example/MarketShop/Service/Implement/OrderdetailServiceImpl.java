package com.example.MarketShop.Service.Implement;

import com.example.MarketShop.DTO.OrderdetailDTO;
import com.example.MarketShop.Model.Orderdetail;
import com.example.MarketShop.Repository.OrderdetailRepository;
import com.example.MarketShop.Service.Interface.OrderdetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class OrderdetailServiceImpl implements OrderdetailService {

    @Autowired
    private OrderdetailRepository orderdetailRepository;
}
