/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.MarketShop.Controller;

import com.example.MarketShop.DTO.ProductDTO;
import com.example.MarketShop.Exception.AppException;
import com.example.MarketShop.DTO.ResponseObject;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.example.MarketShop.Service.Interface.ProductService;
import jakarta.validation.Valid;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getProductList() {
        List<ProductDTO> list = productService.getProductList();

        return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);
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

    @PostMapping("/insert")
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
