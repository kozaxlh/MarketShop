package com.example.MarketShop.API;

import com.example.MarketShop.DTO.ResponseObject;
import com.example.MarketShop.DTO.UsersDTO;
import com.example.MarketShop.DTO.UsersRegisterDTO;
import com.example.MarketShop.Service.Interface.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class RegisterController {

    private ModelMapper modelMapper;
    private UsersService usersService;
    @Autowired
    public RegisterController(ModelMapper modelMapper, UsersService usersService) {
        this.modelMapper = modelMapper;
        this.usersService = usersService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@RequestBody UsersRegisterDTO newUsers) {
        return ResponseEntity.status(200).body(
                new ResponseObject("Success","Dăng ký thành công", usersService.register(newUsers))
        );
    }
}
