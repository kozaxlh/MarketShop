package com.example.MarketShop.Service.Interface;

import com.example.MarketShop.DTO.UsersDTO;
import com.example.MarketShop.DTO.UsersRegisterDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsersService {
    List<UsersDTO> getUsersList(Pageable pageable);

    UsersRegisterDTO register(UsersRegisterDTO users);

    int totalPage();
}
