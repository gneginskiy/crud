package com.tradeservice.services.impl;

import com.tradeservice.ecxeptions.ProductNotFoundException;
import com.tradeservice.entities.Product;
import com.tradeservice.repository.ProductRepository;
import com.tradeservice.services.ProductService;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Product add(Product addProductRq) {
    return productRepository.save(addProductRq);
  }

  @Override
  public Product update(Product updateProductRq, Long productId) {
    productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    return productRepository.save(updateProductRq);
  }

  @Override
  public void delete(Long id) {
    productRepository.delete(productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id)));
  }

  @Override
  public Collection<Product> getAll() {
    return productRepository.findAll();
  }

  @Override
  public Optional<Product> getById(Long id) {
    return productRepository.findById(id);
  }
}
