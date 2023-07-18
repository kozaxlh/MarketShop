package com.example.MarketShop.API;

import com.example.MarketShop.DTO.ResponseObject;
import com.example.MarketShop.DTO.UsersRegisterDTO;
import com.example.MarketShop.Service.Interface.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class AuthenticationController {
    private UsersService usersService;

    @Autowired
    public AuthenticationController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@RequestBody UsersRegisterDTO newUsers) {
        return ResponseEntity.status(200).body(
                new ResponseObject("Success", "Dăng ký thành công", usersService.register(newUsers))
        );
    }
}
