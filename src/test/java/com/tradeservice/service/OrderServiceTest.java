package com.tradeservice.service;

import com.tradeservice.entity.Order;
import com.tradeservice.repository.order.OrderRepository;
import com.tradeservice.service.impl.OrderServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    OrderService    orderService;
    OrderRepository orderRepository;

    @Before
    public void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderService    = new OrderServiceImpl(orderRepository);
    }

    @Test
    public void testAdd() {
     //   orderService.add(ORDER_1);
//        verify(orderRepository, Mockito.times(1)).save(ORDER_1);
//        given(orderRepository.findById(ORDER_1.getOrderId())).willReturn(Optional.of(ORDER_1));
//        Assert.assertEquals(ORDER_1, orderService.getById(ORDER_1.getOrderId()).get());
    }

    @Test
    public void testUpdate() {
//        given(orderRepository.findById(ORDER_1.getOrderId())).willReturn(Optional.of(ORDER_1));
//        orderService.update(ORDER_1, ORDER_1.getOrderId());
//        verify(orderRepository, Mockito.times(1)).save(ORDER_1);
    }

    @Test
    public void testDelete() {
//        given(orderRepository.findById(ORDER_1.getOrderId())).willReturn(Optional.of(ORDER_1));
//        orderService.delete(ORDER_1.getOrderId());
//        verify(orderRepository, Mockito.times(1)).delete(ORDER_1);
    }

    @Test
    public void testGetAll() {
//        given(orderRepository.findAll()).willReturn(ORDER_LIST);
//        Assert.assertTrue(orderService.getAll().containsAll(ORDER_LIST));
    }

    @Test
    public void testGetById() {
//        given(orderRepository.findById(ORDER_1.getOrderId())).willReturn(Optional.of(ORDER_1));
//        Assert.assertEquals(ORDER_1, orderService.getById(ORDER_1.getOrderId()).get());
    }





}