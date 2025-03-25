package com.blandev.inventory.mapper;

import com.blandev.inventory.dto.CategoryDTO;
import com.blandev.inventory.entity.CategoryEntity;

public class CategoryMapper {
  public static CategoryDTO toDTO(CategoryEntity category) {
    return new CategoryDTO(
        category.getId(),
        category.getName(),
        category.getIsActive());
  }

  public static CategoryEntity toEntity(CategoryDTO dto) {
    CategoryEntity category = new CategoryEntity();
    category.setId(dto.id());
    category.setName(dto.name());
    category.setIsActive(dto.isActive());
    return category;
  }
}
