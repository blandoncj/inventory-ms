package com.blandev.inventory.dto;

public record ProductDTO(
    Long id, String sku, String name, String description, Integer stock, Double price,
    Boolean isActive, Long categoryId) {
}
