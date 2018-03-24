package com.yusufcakal.ecommerce.repository;

import com.yusufcakal.ecommerce.model.Product;
import org.springframework.data.repository.CrudRepository;

/**
 *
 */
public interface ProductRepository extends CrudRepository<Product, Long> {}
