package com.tradeservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@Data
@Entity
@Table(name = "product")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  public Product(String name, Double price) {
    this.name = name;
    this.price = price;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productId;

  @NotNull
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @NotNull
  @Column(name = "price", nullable = false)
  private Double price;

  public Product copy(){
    return new Product(name,price);
  }
}
