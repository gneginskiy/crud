package com.tradeservice.util;

import com.tradeservice.entity.Order;
import com.tradeservice.entity.OrderItem;
import com.tradeservice.entity.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public final class TestConstants {
    public  static final Product       PRODUCT_1    = new Product(1L, "Product #12311", 51.0);
    public  static final Product       PRODUCT_2    = new Product(2L, "Product #12312", 52.0);
    public  static final Product       PRODUCT_3    = new Product(3L, "Product #12313", 53.0);
    public  static final List<Product> PRODUCT_LIST = List.of(PRODUCT_1, PRODUCT_2, PRODUCT_3);
    public  static final String        CLIENT_NAME = "Alphabet inc.";

    public static final String POPULATED_ORDERS_ARRAY_JSON = "[{\"orderId\":1,\"" +
            "clientName\":\"Alphabet inc.\"," +
            "\"date\":\"2020-05-03T04:01:03\"," +
            "\"address\":\"Los Angeles, California 90002\"," +
            "\"orderItems\":" +
            "[{\"id\":null,\"product\":{\"productId\":2,\"name\":\"Product #12312\",\"price\":52.0}," +
            "\"count\":116},{\"id\":null,\"product\":{\"productId\":1,\"name\":\"Product #12311\"," +
            "\"price\":51.0},\"count\":115}]}]\n";

    public static final String POPULATED_ORDER_JSON = "{\"orderId\":1,\"" +
            "clientName\":\"Alphabet inc.\"," +
            "\"date\":\"2020-05-03T04:01:03\"," +
            "\"address\":\"Los Angeles, California 90002\"," +
            "\"orderItems\":" +
            "[{\"id\":null,\"product\":{\"productId\":2,\"name\":\"Product #12312\",\"price\":52.0}," +
            "\"count\":116},{\"id\":null,\"product\":{\"productId\":1,\"name\":\"Product #12311\"," +
            "\"price\":51.0},\"count\":115}]}\n";

    public static final Order POPULATED_ORDER = new Order(1l,CLIENT_NAME,
              LocalDateTime.of(2020,5,3,4,1,3),
             "Los Angeles, California 90002", Set.of(new OrderItem(PRODUCT_1, 115), new OrderItem(PRODUCT_2, 116)));
    }

