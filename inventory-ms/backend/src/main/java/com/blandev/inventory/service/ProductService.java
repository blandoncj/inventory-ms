package com.blandev.inventory.service;

import java.util.List;

import com.blandev.inventory.dto.CreateProductDTO;
import com.blandev.inventory.dto.ProductDTO;
import com.blandev.inventory.dto.UpdateProductDTO;

public interface ProductService {

  ProductDTO createProduct(CreateProductDTO productDTO);

  ProductDTO updateProduct(Long id, UpdateProductDTO productDTO);

  ProductDTO getProductById(Long id);

  ProductDTO getProductBySku(String sku);

  List<ProductDTO> getAllProducts();

  List<ProductDTO> getProductsByCategoryId(Long categoryId);

  void increaseStock(Long id, Integer quantity);

  void decreaseStock(Long id, Integer quantity);

  void toggleProductStatus(Long id);

}
