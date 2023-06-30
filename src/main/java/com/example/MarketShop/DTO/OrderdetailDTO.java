package com.example.MarketShop.DTO;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderdetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer productID;

    private String productName;

    private Integer quantity;

    @JsonProperty("amount")
    private Float price;

}
