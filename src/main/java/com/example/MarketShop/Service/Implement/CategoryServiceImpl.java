package com.example.MarketShop.Service.Implement;

import com.example.MarketShop.DTO.CategoryDTO;
import com.example.MarketShop.Model.Category;
import com.example.MarketShop.Repository.CategoryRepository;
import com.example.MarketShop.Service.Interface.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> getCategories() {
        List list = categoryRepository.findAll()
                .stream()
                .map((category) -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
        return list;
    }

}
