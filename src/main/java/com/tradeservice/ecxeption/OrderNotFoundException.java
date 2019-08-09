package com.tradeservice.ecxeption;

public class OrderNotFoundException extends RuntimeException {

  public OrderNotFoundException(Long id) {
    super("Такого заказа нет. id: " + id);
  }
}
