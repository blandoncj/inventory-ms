package com.blandev.inventory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blandev.inventory.dto.CategoryDTO;
import com.blandev.inventory.dto.SaveCategoryDTO;
import com.blandev.inventory.service.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostMapping
  public ResponseEntity<CategoryDTO> createCategory(@RequestBody SaveCategoryDTO categoryDTO) {
    CategoryDTO category = categoryService.createCategory(categoryDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(category);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
    return ResponseEntity.ok(categoryService.getCategoryById(id));
  }

  @GetMapping
  public ResponseEntity<List<CategoryDTO>> getAllCategories() {
    return ResponseEntity.ok(categoryService.getAllCategories());
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody SaveCategoryDTO categoryDTO) {
    return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
  }

  @PatchMapping("/{id}/toggle-status")
  public ResponseEntity<String> toggleCategoryStatus(@PathVariable Long id) {
    categoryService.toggleCategoryStatus(id);
    return ResponseEntity.ok("Category status updated successfully");
  }

}
