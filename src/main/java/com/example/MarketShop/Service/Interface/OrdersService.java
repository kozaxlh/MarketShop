package com.example.MarketShop.Service.Interface;

import com.example.MarketShop.DTO.OrdersDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrdersService {
    List<OrdersDTO> getByEmail(Pageable pageable, String email);

    OrdersDTO addOrder(OrdersDTO ordersDTO, String sessionEmail);
}
