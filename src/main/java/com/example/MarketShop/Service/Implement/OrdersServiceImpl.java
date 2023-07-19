package com.example.MarketShop.Service.Implement;

import com.example.MarketShop.DTO.OrdersDTO;
import com.example.MarketShop.Exception.AppException;
import com.example.MarketShop.Model.Orders;
import com.example.MarketShop.Model.Users;
import com.example.MarketShop.Repository.OrdersRepository;
import com.example.MarketShop.Repository.UsersRepository;
import com.example.MarketShop.Service.Interface.OrderdetailService;
import com.example.MarketShop.Service.Interface.OrdersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {
    private OrdersRepository ordersRepository;
    private OrderdetailService orderdetailService;
    private UsersRepository usersRepository;
    private ModelMapper modelMapper;

    @Autowired
    public OrdersServiceImpl(OrdersRepository ordersRepository, OrderdetailService orderdetailService,
                             UsersRepository usersRepository, ModelMapper modelMapper) {
        this.ordersRepository = ordersRepository;
        this.orderdetailService = orderdetailService;
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrdersDTO> getByEmail(Pageable pageable, String email) {
        Users user = usersRepository.findByEmail(email).get();

        List<OrdersDTO> result = ordersRepository.findByCustomer(pageable, user).getContent()
                .stream()
                .map((element) -> modelMapper.map(element, OrdersDTO.class))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public OrdersDTO addOrder(OrdersDTO ordersDTO, String sessionEmail) {
        Users user = usersRepository.findByEmail(sessionEmail).get();

        Orders orders = modelMapper.map(ordersDTO, Orders.class);

        if (orders.getProductList().isEmpty()) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Không có sản phẩm trong giỏ hàng");
        }

        return modelMapper.map(ordersRepository.save(orders), OrdersDTO.class);
    }
}
