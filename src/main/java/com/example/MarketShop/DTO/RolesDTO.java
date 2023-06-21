package com.example.MarketShop.DTO;


import lombok.Data;

import java.io.Serializable;

@Data
public class RolesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer roleId;

    private String roleName;

}
