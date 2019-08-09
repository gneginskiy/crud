package com.tradeservice.services;

import com.tradeservice.entities.Product;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProductService {

  Product add(Product product);

  Product update(Product newProduct, Long id);

  void delete(Long id);

  Collection<Product> getAll();

  Optional<Product> getById(Long id);
}
