/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.MarketShop.API;

import com.example.MarketShop.DTO.PageObject;
import com.example.MarketShop.DTO.ProductDTO;
import com.example.MarketShop.Exception.AppException;
import com.example.MarketShop.DTO.ResponseObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.MarketShop.Service.Interface.ProductService;
import jakarta.validation.Valid;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {
    final private String DEFAULT_PAGE_LIMIT = "5";
    final private String DEFAULT_PAGE = "1";

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<ResponseObject> getProductList(
            @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_LIMIT) int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        PageObject result = new PageObject();
        result.setPage(page);
        result.setList(productService.getProductList(pageable));
        result.setTotalPage((int) Math.ceil( (double) productService.totalPage() / limit));

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Success", "Đã lấy được sản phẩm", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findProductByID(@PathVariable Integer id) {
        ProductDTO product = productService.findByID(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Sản phẩm đã tìm thấy", product)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateProduct(@RequestBody ProductDTO input, @PathVariable Integer id) {
        input.setProductID(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Sản phẩn đã sửa thành công",
                        productService.updateProduct(input))
        );
    }

    @PostMapping()
    public ResponseEntity<ResponseObject> addProduct(@RequestBody @Valid ProductDTO input) {
        ProductDTO product = productService.addProduct(input);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Sản phẩm đã thêm thành công", product)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Xóa thành công", productService.deleteProduct(id)));
    }

    @ExceptionHandler(AppException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseObject> duplicatedNameException(AppException e) {
        return ResponseEntity.status(e.getCode()).body(
                new ResponseObject("Failed", e.getMessege(), "")
        );
    }

}
