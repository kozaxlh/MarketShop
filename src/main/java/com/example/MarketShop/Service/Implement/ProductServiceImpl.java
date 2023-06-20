/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.MarketShop.Service.Implement;

import com.example.MarketShop.DTO.ProductDTO;
import com.example.MarketShop.Exception.AppException;
import com.example.MarketShop.Model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.MarketShop.Repository.ProductRepository;
import com.example.MarketShop.Service.Interface.ProductService;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Admin
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getProductList() {
        List list = productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public ProductDTO findByID(Integer productID) {
        Optional<Product> product = productRepository.findById(productID);
        if (!product.isPresent()) {
            throw new AppException(HttpStatus.OK, "Không tìm thấy sản phẩm");
        }
        return modelMapper.map(product.get(), ProductDTO.class);
    }

    @Override
    public ProductDTO addProduct(ProductDTO newProduct) {
        Product product = modelMapper.map(newProduct, Product.class);
        checkExistedProductName(product);
        return modelMapper.map(productRepository.save(product), ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO newProduct) {
        Product product = modelMapper.map(newProduct, Product.class);
        checkExistedProductName(product);
        ProductDTO updatedProduct = productRepository.findById(product.getProductID())
                .map(item -> {
                    item.setProductName(product.getProductName());
                    item.setUnit(product.getUnit());
                    item.setAmount(product.getAmount());
                    item.setPrice(product.getPrice());
                    item.setImage(product.getImage());
                    item.setCategory(product.getCategory());
                    return modelMapper.map(productRepository.save(item), ProductDTO.class);
                }).orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Không tìm thấy sản phẩm"));
        return updatedProduct;
    }

    @Override
    public String deleteProduct(Integer productID) {
        Optional<Product> product = productRepository.findById(productID);
        if (product.isEmpty()) {
            throw new AppException(HttpStatus.NOT_FOUND, "Không tìm thấy sản phẩm để xóa");
        }
        productRepository.delete(product.get());
        return "";

    }

    private void checkExistedProductName(Product product) {
        boolean existedProductName = productRepository.findByProductName(product.getProductName()).isPresent();
        if (existedProductName) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Tên sản phẩm đã tồn tại");
        }
    }

}
