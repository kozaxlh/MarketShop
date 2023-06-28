package com.example.MarketShop.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RolesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty("roleID")
    private Integer id;

    private String roleName;

}
