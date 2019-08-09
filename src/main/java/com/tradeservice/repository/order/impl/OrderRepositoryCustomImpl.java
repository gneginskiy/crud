package com.tradeservice.repository.order.impl;

import com.tradeservice.entities.Order;
import com.tradeservice.repository.order.OrderRepositoryBasic;
import com.tradeservice.repository.order.OrderRepositoryCustom;

import javax.persistence.EntityManager;

public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {
    private final OrderRepositoryBasic orderRepositoryBasic;
    private final EntityManager        entityManager;

    // constructor-based injection
    public OrderRepositoryCustomImpl(OrderRepositoryBasic orderRepositoryBasic, EntityManager entityManager1) {
        this.orderRepositoryBasic = orderRepositoryBasic;
        this.entityManager = entityManager1;
    }

    @Override
    public Order saveAndRefresh(Order order) {
        orderRepositoryBasic.save(order);
        entityManager.refresh(order);
        return order;
    }
}
