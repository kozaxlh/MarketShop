package com.example.MarketShop.API;

import com.example.MarketShop.DTO.PageObject;
import com.example.MarketShop.DTO.ResponseObject;
import com.example.MarketShop.DTO.UsersDTO;
import com.example.MarketShop.Service.Interface.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final String DEFAULT_PAGE_LIMIT = "5";
    private final String DEFAULT_PAGE = "1";
    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<ResponseObject<UsersDTO>> getUsersList(
            @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_LIMIT) int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        PageObject pages = new PageObject();
        pages.setPage(page);
        pages.setList(usersService.getUsersList(pageable));
        pages.setTotalPage((int) Math.ceil((double) usersService.totalPage() / limit));

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Lấy user thành công", pages)
        );
    }
}
