package com.tradeservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeservice.entity.Product;
import lombok.SneakyThrows;

import java.util.List;

import static java.util.stream.Collectors.toList;

public final class TestUtils {
    public  static final Product       PRODUCT_1    = new Product(1L, "Product #12311", 51.0);
    public  static final Product       PRODUCT_2    = new Product(2L, "Product #12312", 52.0);
    public  static final Product       PRODUCT_3    = new Product(3L, "Product #12313", 53.0);
    public  static final List<Product> PRODUCT_LIST = List.of(PRODUCT_1, PRODUCT_2, PRODUCT_3);

    @SneakyThrows
    public static String asJsonString(final Object obj) {
        return new ObjectMapper().writeValueAsString(obj);
    }

}
