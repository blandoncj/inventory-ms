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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blandev.inventory.dto.CreateProductDTO;
import com.blandev.inventory.dto.ProductDTO;
import com.blandev.inventory.dto.UpdateProductDTO;
import com.blandev.inventory.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
    return ResponseEntity.ok(productService.getProductById(id));
  }

  @GetMapping("/sku/{sku}")
  public ResponseEntity<ProductDTO> getProductBySku(@PathVariable String sku) {
    return ResponseEntity.ok(productService.getProductBySku(sku));
  }

  @GetMapping
  public ResponseEntity<List<ProductDTO>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  @GetMapping("/category/{categoryId}")
  public ResponseEntity<List<ProductDTO>> getProductsByCategoryId(@PathVariable Long categoryId) {
    return ResponseEntity.ok(productService.getProductsByCategoryId(categoryId));
  }

  @PostMapping
  public ResponseEntity<ProductDTO> createProduct(@RequestBody CreateProductDTO productDTO) {
    ProductDTO product = productService.createProduct(productDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(product);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody UpdateProductDTO productDTO) {
    return ResponseEntity.ok(productService.updateProduct(id, productDTO));
  }

  @PatchMapping("/{id}/increase-stock")
  public ResponseEntity<String> increaseStock(@PathVariable Long id, @RequestParam Integer quantity) {
    productService.increaseStock(id, quantity);
    return ResponseEntity.ok("Stock increased by " + quantity);
  }

  @PatchMapping("/{id}/decrease-stock")
  public ResponseEntity<String> decreaseStock(@PathVariable Long id, @RequestParam Integer quantity) {
    productService.decreaseStock(id, quantity);
    return ResponseEntity.ok("Stock decreased by " + quantity);
  }

  @PatchMapping("/{id}/toggle-status")
  public ResponseEntity<String> toggleProductStatus(@PathVariable Long id) {
    productService.toggleProductStatus(id);
    return ResponseEntity.ok("Product status updated successfully");
  }

}
