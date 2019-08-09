package com.tradeservice.util;

import com.tradeservice.entity.Order;
import com.tradeservice.entity.OrderItem;
import com.tradeservice.entity.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public final class TestConstants {
    public  static final Product       PRODUCT_1    = new Product(1L, "Товар #12311", 51.0);
    public  static final Product       PRODUCT_2    = new Product(2L, "Товар #12312", 52.0);
    public  static final Product       PRODUCT_3    = new Product(3L, "Товар #12313", 53.0);
    private  static final List<Product> PRODUCT_LIST = List.of(PRODUCT_1, PRODUCT_2, PRODUCT_3);

    public static final Set<OrderItem> ORDER_ITEMS_1 = Set.of(new OrderItem(PRODUCT_1, 115), new OrderItem(PRODUCT_2, 116));

    public static List<Product> getProductsList(){
       return PRODUCT_LIST.stream().map(Product::copy).collect(toList());
    }



}
