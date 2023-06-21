package com.example.MarketShop.API;

import com.example.MarketShop.DTO.PageObject;
import com.example.MarketShop.DTO.ResponseObject;
import com.example.MarketShop.DTO.UsersDTO;
import com.example.MarketShop.Service.Interface.UsersService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    final private String DEFAULT_PAGE_LIMIT = "5";
    final private String DEFAULT_PAGE = "1";
    @Autowired
    private UsersService usersService;

//    @GetMapping("/{id}")
//    public UsersDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
//        return usersService.getById(id);
//    }
//
    @GetMapping
    public ResponseEntity<ResponseObject> getUsersList(
            @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_LIMIT) int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        PageObject result = new PageObject();
        result.setPage(page);
        result.setList(usersService.getUsersList(pageable));
        result.setTotalPage((int) Math.ceil( (double) usersService.totalPage() / limit));

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success","Lấy user thành công",result)
        );
    }
//
//    @PostMapping
//    public String save(@) {
//        return usersService.save(vO).toString();
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
//        usersService.delete(id);
//    }
//
//    @PutMapping("/{id}")
//    public void update(@Valid @NotNull @PathVariable("id") Integer id) {
//        usersService.update(id, vO);
//    }


}
