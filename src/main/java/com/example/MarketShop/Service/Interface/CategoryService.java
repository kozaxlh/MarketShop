package com.example.MarketShop.Service.Interface;

import com.example.MarketShop.DTO.CategoryDTO;
import com.example.MarketShop.Model.Category;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getCategories(Pageable pageable);

    CategoryDTO findByID(Integer categoryID);

    CategoryDTO addCategory(CategoryDTO newCategory);

    CategoryDTO updateCategory(CategoryDTO updatedCategory);

    String deleteCategory(Integer categoryID);

    void checkExistedCategory(Category category);

    int totalPage();
}
