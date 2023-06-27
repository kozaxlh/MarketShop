package com.example.MarketShop.Service.Implement;

import com.example.MarketShop.DTO.CategoryDTO;
import com.example.MarketShop.Exception.AppException;
import com.example.MarketShop.Model.Category;
import com.example.MarketShop.Repository.CategoryRepository;
import com.example.MarketShop.Service.Interface.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getCategories(Pageable pageable) {
        List list = categoryRepository.findAll()
                .stream()
                .map((category) -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public CategoryDTO findByID(Integer categoryID) {
        Optional<Category> category = categoryRepository.findById(categoryID);
        if(category.isEmpty()) {
            throw new AppException(HttpStatus.OK, "Không tìm thấy danh mục cần tìm");
        }

        return modelMapper.map(category.get(), CategoryDTO.class);
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO newCategory) {
        Category category = modelMapper.map(newCategory, Category.class);
        checkExistedCategory(category);
        return modelMapper.map(categoryRepository.save(category), CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO updatedCategory) {
        Category category = modelMapper.map(updatedCategory, Category.class);
        checkExistedCategory(category);
        CategoryDTO dto = categoryRepository.findById(updatedCategory.getCategoryID())
                .map(item -> {
                    item.setName(category.getName());
                    item.setDescription(category.getDescription());
                    return modelMapper.map(categoryRepository.save(item), CategoryDTO.class);
                }).orElseThrow(() -> new AppException(HttpStatus.OK, "Không tìm thấy danh mục"));
        return dto;
    }

    @Override
    public String deleteCategory(Integer categoryID) {
        Optional<Category> category = categoryRepository.findById(categoryID);

        if(category.isEmpty()) {
            throw new AppException(HttpStatus.OK, "Không tìm thấy danh mục");
        }
        try {
            categoryRepository.delete(category.get());
        }
        catch (Exception e) {
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "Không thể xóa");
        }
        return "";
    }

    @Override
    public void checkExistedCategory(Category category) {
        Optional<Category> checkedCategory = categoryRepository.findByName(category.getName());

        boolean existedProductName = checkedCategory.isPresent() && !checkedCategory.get().getCategoryID().equals(category.getCategoryID());
        if (existedProductName) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Tên danh mục đã tồn tại");
        }
    }

    @Override
    public int totalPage(){
        return (int) categoryRepository.count() ;
    };

}
