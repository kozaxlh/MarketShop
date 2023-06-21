package com.example.MarketShop.DTO;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrdersDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long orderID;

    private Integer customerID;

    private LocalDate date;

    private Float total;

    private String note;

    private List<OrderdetailDTO> productList;

}
