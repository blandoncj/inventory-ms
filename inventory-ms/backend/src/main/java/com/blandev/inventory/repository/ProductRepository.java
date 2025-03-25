package com.blandev.inventory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blandev.inventory.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

  @Modifying
  @Transactional
  @Query("UPDATE ProductEntity p SET p.stock = p.stock + ?2 WHERE p.id = ?1")
  int increaseStock(Long id, Integer quantity);

  @Modifying
  @Transactional
  @Query("UPDATE ProductEntity p SET p.stock = p.stock - ?2 WHERE p.id = ?1")
  int decreaseStock(Long id, Integer quantity);

  List<ProductEntity> findByCategoryId(Long categoryId);

  Optional<ProductEntity> findBySku(String sku);

  boolean existsBySku(String sku);

}
