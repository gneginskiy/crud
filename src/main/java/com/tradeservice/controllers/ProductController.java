package com.tradeservice.controllers;

import com.tradeservice.ecxeptions.ProductNotFoundException;
import com.tradeservice.entities.Product;
import com.tradeservice.services.ProductService;

import java.util.Collection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/products")
public class ProductController {

  private ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  /**
   * 6) - добавление нового товара
   * @param product добавляемый товар
   * @return HttpStatus.CREATED if ok, and added Product
   * POST http://localhost:8080/product/ JSON:
   */
  @PostMapping
  public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    return ResponseEntity.status(HttpStatus.CREATED).body(productService.add(product));
  }

  /**
   * 7) - изменение существующего товара, поиск в базе по id
   * @param newProduct товар
   * @param id id изменяемого
   * @return HttpStatus.ACCEPTED, сам товар, измененный
   * PUT http://localhost:8080/product/{id} JSON:
   */
  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
    return ResponseEntity.accepted().body(productService.update(newProduct, id));
  }

  /**
   * 8)	- удаление товара
   * @param id - id товара
   * @return HttpStatus.ACCEPTED
   * DELETE http://localhost:8080/product/{id}
   */
  @DeleteMapping("/{id}")
  public ResponseEntity deleteProduct(@PathVariable Long id) {
    productService.delete(id);
    return ResponseEntity.accepted().build();
  }

  /**
   * 9) - получение всех товаров
   * @return HttpStatus.OK, List все товары
   * GET http://localhost:8080/product
   */
  @GetMapping
  public ResponseEntity<Collection<Product>> getAllProduct() {
    return ResponseEntity.ok(productService.getAll());
  }

  /**
   * 10)	- получение определенного товара по id
   * @param id - id товара
   * @return HttpStatus.OK, товар, если найден
   * GET http://localhost:8080/catalog/{id}
   */
  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    return ResponseEntity.ok(productService.getById(id).orElseThrow(() -> new ProductNotFoundException(id)));
  }
}
