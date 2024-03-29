package com.example.MarketShop.Service.Implement;

import com.example.MarketShop.DTO.ProductDTO;
import com.example.MarketShop.Exception.AppException;
import com.example.MarketShop.Model.Product;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.MarketShop.Repository.ProductRepository;
import com.example.MarketShop.Service.Interface.ProductService;

import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

/**
 * @author Admin
 */
@Service
public class ProductServiceImpl implements ProductService {
    private ModelMapper modelMapper;
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getProductList(Pageable pageable) {
        List list = productRepository.findAll(pageable).getContent()
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public ProductDTO findByID(Integer productID) {
        Optional<Product> product = productRepository.findById(productID);
        if (product.isEmpty() || product.get().getDelete_at() != null) {
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
                    item.setUpdate_at(LocalDateTime.now());
                    return modelMapper.map(productRepository.save(item), ProductDTO.class);
                }).orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Không tìm thấy sản phẩm"));
        return updatedProduct;
    }

    @Override
    public String deleteProduct(Integer productID) {
        Optional<Product> product = productRepository.findById(productID);
        if (product.isEmpty() || product.get().getDelete_at() != null) {
            throw new AppException(HttpStatus.NOT_FOUND, "Không tìm thấy sản phẩm để xóa");
        }
        productRepository.delete(String.valueOf(productID));
        return "";

    }

    private void checkExistedProductName(Product product) {
        Optional<Product> checkedProduct = productRepository.findByProductName(product.getProductName());

        boolean existedProductName = checkedProduct.isPresent() && !checkedProduct.get().getProductID().equals(product.getProductID());
        if (existedProductName) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Tên sản phẩm đã tồn tại");
        }
    }

    @Override
    public int totalPage() {
        return (int) productRepository.count();
    }

    ;
}
