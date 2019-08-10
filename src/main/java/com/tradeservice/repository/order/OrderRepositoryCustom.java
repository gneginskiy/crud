package com.tradeservice.repository.order;

import com.tradeservice.entity.Order;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepositoryCustom {
    Order saveAndRefresh(Order order);
}
