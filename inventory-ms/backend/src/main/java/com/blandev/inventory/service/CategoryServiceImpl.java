package com.blandev.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.blandev.inventory.dto.CategoryDTO;
import com.blandev.inventory.dto.SaveCategoryDTO;
import com.blandev.inventory.entity.CategoryEntity;
import com.blandev.inventory.exception.ConflictException;
import com.blandev.inventory.exception.ResourceNotFoundException;
import com.blandev.inventory.mapper.CategoryMapper;
import com.blandev.inventory.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public CategoryDTO createCategory(SaveCategoryDTO categoryDTO) {
    if (categoryRepository.existsByName(categoryDTO.name())) {
      throw new ConflictException("Category with name: '" + categoryDTO.name() + "' already exists");
    }

    CategoryEntity category = new CategoryEntity();
    category.setName(categoryDTO.name());
    category.setIsActive(true);

    category = categoryRepository.save(category);
    return CategoryMapper.toDTO(category);
  }

  @Override
  public CategoryDTO updateCategory(Long id, SaveCategoryDTO categoryDTO) {
    CategoryEntity category = categoryRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));

    if (!category.getName().equals(categoryDTO.name()) && categoryRepository.existsByName(categoryDTO.name())) {
      throw new ConflictException("Category with name: '" + categoryDTO.name() + "' already exists");
    }

    category.setName(categoryDTO.name());
    category = categoryRepository.save(category);

    return CategoryMapper.toDTO(category);
  }

  @Override
  public CategoryDTO getCategoryById(Long id) {
    CategoryEntity category = categoryRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));
    return CategoryMapper.toDTO(category);
  }

  @Override
  public List<CategoryDTO> getAllCategories() {
    List<CategoryEntity> categories = categoryRepository.findAll();
    return categories.stream().map(CategoryMapper::toDTO).toList();
  }

  @Override
  public void toggleCategoryStatus(Long id) {
    CategoryEntity category = categoryRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));
    category.setIsActive(!category.getIsActive());
    categoryRepository.save(category);
  }

}
