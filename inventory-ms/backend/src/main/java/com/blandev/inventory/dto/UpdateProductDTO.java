package com.blandev.inventory.dto;

public record UpdateProductDTO(
    String name, String description, Double price, Long categoryId) {
}
