package com.yusufcakal.ecommerce.repository;

import com.yusufcakal.ecommerce.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByCategoryId(Long id);

}
