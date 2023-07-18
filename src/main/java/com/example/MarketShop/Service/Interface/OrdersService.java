package com.example.MarketShop.Service.Interface;

import com.example.MarketShop.DTO.OrdersDTO;

public interface OrdersService {
    OrdersDTO addOrder(OrdersDTO ordersDTO, String sessionEmail);
}
