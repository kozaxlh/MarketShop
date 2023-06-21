package com.example.MarketShop.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageObject {
    private int page;
    private int totalPage;
    private List list = new ArrayList<>();
}
