package com.tradeservice.db;

import com.tradeservice.entity.OrderItem;
import com.tradeservice.entity.Product;
import com.tradeservice.entity.Order;
import com.tradeservice.service.ProductService;
import com.tradeservice.service.OrderService;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class InitDbRunner {

  @Bean
  CommandLineRunner initDataBase(ProductService productService, OrderService orderService) {
    Product newProduct1 = new Product("Товар 1", 59.0);
    Product newProduct2 = new Product("Товар 2", 999.0);
    Set<OrderItem> orderItemSet1 = Set.of(new OrderItem(newProduct1, 11), new OrderItem(newProduct2, 11));
    Order newOrder1 = new Order("Клиент 1", LocalDateTime.now(), "Адрес 1",orderItemSet1);

    Set<OrderItem> orderItemSet2 = Set.of(new OrderItem(newProduct1, 2211), new OrderItem(newProduct2, 12));
    Order newOrder2 = new Order("Клиент 2", LocalDateTime.now(), "Адрес 2",orderItemSet2);

    return args -> {

      log.info("Add new product1: " + productService.add(newProduct1));
      log.info("Add new product2: " + productService.add(newProduct2));
      log.info("Add new order1: " + orderService.add(newOrder1));
      log.info("Add new order2: " + orderService.add(newOrder2));
    };

  }
}
