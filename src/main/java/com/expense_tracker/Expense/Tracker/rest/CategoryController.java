package com.expense_tracker.Expense.Tracker.rest;

import com.expense_tracker.Expense.Tracker.model.*;
import com.expense_tracker.Expense.Tracker.service.CategoryDetails;
import com.expense_tracker.Expense.Tracker.service.User;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    CategoryDetails categoryDetails;
    User user;
    private final ModelMapper modelMapper;
    CategoryController(CategoryDetails categoryDetails,ModelMapper modelMapper,User user) {
        this.categoryDetails=categoryDetails;
        this.modelMapper=modelMapper;
        this.user=user;
    }


    @PostMapping("/category")
    public ResponseEntity<CategoryResponseDTO> saveCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        UserDetails userDetails = user.getUser(categoryRequestDTO.userId());
        Category category = new Category(categoryRequestDTO.categoryName(),categoryRequestDTO.categoryType(),userDetails);
        category.addUser(userDetails);
        int categoryId =  categoryDetails.saveCategoryDAO(category);
        return ResponseEntity.ok(new CategoryResponseDTO(categoryId));
    }

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> deleteCategory(@PathVariable int categoryId) {
        int categoryID = categoryDetails.deleteCategoryDAO(categoryId);
        return ResponseEntity.ok(new CategoryResponseDTO(categoryID));
    }

    @GetMapping("category/{categoryId}")
    public ResponseEntity<CategoryResponseDetailsDTO> getCategory(@PathVariable int categoryId) {
        Category category = categoryDetails.getCategoryDAO(categoryId);
        CategoryResponseDetailsDTO categoryResponseDetailsDTO = new CategoryResponseDetailsDTO(category.getCategoryId(),category.getUser_category().getUserId(),category.getCategoryName(),category.getCategoryType());
        return ResponseEntity.ok(categoryResponseDetailsDTO);
    }

    @PutMapping("/category")
    public ResponseEntity<CategoryResponseDTO> modifyCategoryDetails(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        UserDetails userDetails = user.getUser(categoryRequestDTO.userId());
        Category category = new Category(categoryRequestDTO.categoryName(),categoryRequestDTO.categoryType(),userDetails);
        category.addUser(userDetails);
        category.setCategoryId(categoryRequestDTO.categoryId());
        int categoryId =  categoryDetails.modifyCategoryDAO(category);
        return ResponseEntity.ok(new CategoryResponseDTO(categoryId));
    }

}
