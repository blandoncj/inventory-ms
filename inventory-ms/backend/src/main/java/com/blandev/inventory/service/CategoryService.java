package com.blandev.inventory.service;

import java.util.List;

import com.blandev.inventory.dto.CategoryDTO;
import com.blandev.inventory.dto.SaveCategoryDTO;

public interface CategoryService {

  CategoryDTO createCategory(SaveCategoryDTO categoryDTO);

  CategoryDTO updateCategory(Long id, SaveCategoryDTO categoryDTO);

  CategoryDTO getCategoryById(Long id);

  List<CategoryDTO> getAllCategories();

  void toggleCategoryStatus(Long id);

}
