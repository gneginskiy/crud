package com.tradeservice.ecxeption;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(Long id) {
    super("Такого товара нет. id:" + id);
  }
}
