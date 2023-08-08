package com.example.MarketShop.Service.Implement;

import com.example.MarketShop.DTO.OrdersDTO;
import com.example.MarketShop.Exception.AppException;
import com.example.MarketShop.Model.OrderdetailPK;
import com.example.MarketShop.Model.Orders;
import com.example.MarketShop.Model.Users;
import com.example.MarketShop.Repository.OrdersRepository;
import com.example.MarketShop.Repository.UsersRepository;
import com.example.MarketShop.Service.Interface.OrdersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {
    private OrdersRepository ordersRepository;
    private UsersRepository usersRepository;
    private ModelMapper modelMapper;

    @Autowired
    public OrdersServiceImpl(OrdersRepository ordersRepository, UsersRepository usersRepository, ModelMapper modelMapper) {
        this.ordersRepository = ordersRepository;
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
        if (ordersDTO.getProductList() == null || ordersDTO.getProductList().isEmpty()) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Không có sản phẩm trong giỏ hàng");
        }

        Users user = usersRepository.findByEmail(sessionEmail).get();

        Orders orders = modelMapper.map(ordersDTO, Orders.class);
        orders.setCustomer(user);
        orders.setTotal(
                orders.getProductList().stream()
                        .map(item -> item.getPrice())
                        .reduce(0f, (total, item) -> total + item)
        );

        int insertID = ordersRepository.getAutoIncrementID();
        orders.getProductList().stream()
                .forEach(product ->
                        product.setOrderdetailPK(new OrderdetailPK(
                                insertID,
                                product.getProduct().getProductID()
                        ))
                );

        try {
            OrdersDTO result = modelMapper.map(ordersRepository.save(orders), OrdersDTO.class);
            result.setDate(LocalDateTime.now());
            return result;
        } catch (DataIntegrityViolationException e) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Không đủ hàng trong kho");
        }
    }
}
