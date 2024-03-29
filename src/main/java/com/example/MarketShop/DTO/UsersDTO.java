package com.example.MarketShop.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

/**
 * @author Admin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("UsersInfo")
public class UsersDTO implements Serializable {
    private Integer userID;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    private String password;

    private String fullname;

    private String address;

    private String city;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime create_at;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime update_at;

    private Set usersRole;

    @JsonProperty("ordersList")
    private List<OrdersDTO> orders;
}
