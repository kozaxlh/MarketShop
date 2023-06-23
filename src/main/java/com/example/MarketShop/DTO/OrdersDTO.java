package com.example.MarketShop.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdersDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long orderID;

    private Integer customerID;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = JsonFormat.DEFAULT_TIMEZONE)
    private LocalDateTime date;

    private Float total;

    private String note;

    private List<OrderdetailDTO> productList;

}
