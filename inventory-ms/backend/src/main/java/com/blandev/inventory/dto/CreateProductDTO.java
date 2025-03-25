package com.blandev.inventory.dto;

public record CreateProductDTO(
    String sku, String name,
    String description, Integer stock,
    Double price, Long categoryId) {

}
