package com.example.MarketShop.API;

import com.example.MarketShop.DTO.OrdersDTO;
import com.example.MarketShop.DTO.PageObject;
import com.example.MarketShop.DTO.ResponseObject;
import com.example.MarketShop.Repository.OrdersRepository;
import com.example.MarketShop.Service.Interface.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    final private String DEFAULT_PAGE_LIMIT = "5";
    final private String DEFAULT_PAGE = "1";
    private OrdersService ordersService;
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersController(OrdersService ordersService, OrdersRepository ordersRepository) {
        this.ordersService = ordersService;
        this.ordersRepository = ordersRepository;
    }

    @GetMapping
    public ResponseEntity<ResponseObject<OrdersDTO>> getByEmail(
            Authentication authentication,
            @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_LIMIT) int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        PageObject result = new PageObject();
        result.setPage(page);
        result.setList(ordersService.getByEmail(pageable, authentication.getName()));
        result.setTotalPage((int) Math.ceil(result.getList().size() / limit));

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Đã lấy được danh sách đơn hàng", result));
    }

    @PostMapping
    public ResponseEntity<ResponseObject<OrdersDTO>> addOrder(
            Authentication authentication,
            @RequestBody OrdersDTO ordersDTO) {
        OrdersDTO orders = ordersService.addOrder(ordersDTO, authentication.getName());

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject<>("Success", "Đơn hàng đã được ghi nhận", orders));
    }
}
