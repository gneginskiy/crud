package com.tradeservice.repository.order;

import com.tradeservice.entities.Order;

public interface OrderRepositoryCustom {
    Order saveAndRefresh(Order order);
}
