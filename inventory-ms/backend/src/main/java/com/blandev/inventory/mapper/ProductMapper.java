package com.blandev.inventory.mapper;

import com.blandev.inventory.dto.ProductDTO;
import com.blandev.inventory.entity.ProductEntity;

public class ProductMapper {
  public static ProductDTO toDTO(ProductEntity product) {
    return new ProductDTO(
        product.getId(),
        product.getSku(),
        product.getName(),
        product.getDescription(),
        product.getStock(),
        product.getPrice(),
        product.getIsActive(),
        product.getCategory().getId());
  }

  public static ProductEntity toEntity(ProductDTO dto) {
    ProductEntity product = new ProductEntity();
    product.setId(dto.id());
    product.setSku(dto.sku());
    product.setName(dto.name());
    product.setDescription(dto.description());
    product.setStock(dto.stock());
    product.setPrice(dto.price());
    product.setIsActive(dto.isActive());
    return product;
  }
}
