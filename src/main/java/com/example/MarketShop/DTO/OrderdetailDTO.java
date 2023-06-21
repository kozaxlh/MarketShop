package com.example.MarketShop.DTO;


import lombok.Data;

import java.io.Serializable;

@Data
public class OrderdetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer productID;

    private Integer quantity;

    private Float price;

}
