package com.blandev.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blandev.inventory.dto.CreateProductDTO;
import com.blandev.inventory.dto.ProductDTO;
import com.blandev.inventory.dto.UpdateProductDTO;
import com.blandev.inventory.entity.CategoryEntity;
import com.blandev.inventory.entity.ProductEntity;
import com.blandev.inventory.exception.ConflictException;
import com.blandev.inventory.exception.InsufficientStockException;
import com.blandev.inventory.exception.InvalidOperationException;
import com.blandev.inventory.exception.ResourceNotFoundException;
import com.blandev.inventory.mapper.ProductMapper;
import com.blandev.inventory.repository.CategoryRepository;
import com.blandev.inventory.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public ProductDTO getProductById(Long id) {
    ProductEntity product = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
    return ProductMapper.toDTO(product);
  }

  @Override
  public ProductDTO getProductBySku(String sku) {
    ProductEntity product = productRepository.findBySku(sku)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found with SKU: " + sku));
    return ProductMapper.toDTO(product);
  }

  @Override
  public List<ProductDTO> getAllProducts() {
    List<ProductEntity> products = productRepository.findAll();
    return products.stream().map(ProductMapper::toDTO).toList();
  }

  @Override
  public List<ProductDTO> getProductsByCategoryId(Long categoryId) {
    categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + categoryId));

    List<ProductEntity> products = productRepository.findByCategoryId(categoryId);
    return products.stream().map(ProductMapper::toDTO).toList();
  }

  @Override
  public ProductDTO createProduct(CreateProductDTO productDTO) {
    if (productRepository.existsBySku(productDTO.sku())) {
      throw new ConflictException("Product with SKU: '" + productDTO.sku() + "' already exists");
    }

    CategoryEntity category = categoryRepository.findById(productDTO.categoryId())
        .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + productDTO.categoryId()));

    ProductEntity product = new ProductEntity();
    product.setSku(productDTO.sku());
    product.setName(productDTO.name());
    product.setDescription(productDTO.description());
    product.setPrice(productDTO.price());
    product.setStock(productDTO.stock());
    product.setCategory(category);
    product.setIsActive(true);

    product = productRepository.save(product);
    return ProductMapper.toDTO(product);
  }

  @Override
  public ProductDTO updateProduct(Long id, UpdateProductDTO productDTO) {
    ProductEntity product = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

    CategoryEntity category = categoryRepository.findById(productDTO.categoryId())
        .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + productDTO.categoryId()));

    product.setName(productDTO.name());
    product.setDescription(productDTO.description());
    product.setPrice(productDTO.price());
    product.setCategory(category);

    product = productRepository.save(product);
    return ProductMapper.toDTO(product);
  }

  @Override
  public void increaseStock(Long id, Integer quantity) {
    productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

    if (quantity < 0) {
      throw new InvalidOperationException("Quantity must be a positive number");
    }

    productRepository.increaseStock(id, quantity);
  }

  @Override
  public void decreaseStock(Long id, Integer quantity) {
    ProductEntity product = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

    if (quantity < 0) {
      throw new InvalidOperationException("Quantity must be a positive number");
    }

    if (quantity > product.getStock()) {
      throw new InsufficientStockException("Insufficient stock for product with ID: " + id);
    }

    productRepository.decreaseStock(id, quantity);
  }

  @Override
  public void toggleProductStatus(Long id) {
    ProductEntity product = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
    product.setIsActive(!product.getIsActive());
    productRepository.save(product);
  }

}
