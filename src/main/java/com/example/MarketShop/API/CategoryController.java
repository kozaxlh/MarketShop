package com.example.MarketShop.API;

import com.example.MarketShop.DTO.CategoryDTO;
import com.example.MarketShop.DTO.PageObject;
import com.example.MarketShop.DTO.ResponseObject;
import com.example.MarketShop.Service.Interface.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final String DEFAULT_PAGE_LIMIT = "5";
    private final String DEFAULT_PAGE = "1";
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ResponseObject> showCategory(
            @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_LIMIT) int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        PageObject pages = new PageObject();
        pages.setPage(page);
        pages.setList(categoryService.getCategories(pageable));
        pages.setTotalPage((int) Math.ceil( (double) categoryService.totalPage() / limit));

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success","Lấy danh mục thành công", pages)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findByID(@PathVariable Integer id) {
        CategoryDTO categoryDTO = categoryService.findByID(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject<>("Success", "Tìm thấy", categoryDTO)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody CategoryDTO input, @PathVariable Integer id) {
        input.setCategoryID(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Sửa danh mục thành công", categoryService.updateCategory(input))
        );
    }

    @PostMapping
    public ResponseEntity<ResponseObject> add(@RequestBody @Valid CategoryDTO input) {
        CategoryDTO categoryDTO = categoryService.addCategory(input);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject<>("Success", "Đã thêm", categoryDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Xóa thành công", categoryService.deleteCategory(id))
        );
    }
}
