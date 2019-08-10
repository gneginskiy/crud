package com.tradeservice.service;

import com.tradeservice.entity.Order;
import com.tradeservice.entity.OrderItem;
import com.tradeservice.repository.order.OrderRepository;
import com.tradeservice.service.impl.OrderServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static com.tradeservice.util.TestConstants.PRODUCT_1;
import static com.tradeservice.util.TestConstants.PRODUCT_2;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    OrderService    orderService;
    OrderRepository orderRepository;
    Order           populatedOrder;

    @Before
    public void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderService    = new OrderServiceImpl(orderRepository);
        populatedOrder  = getPopulatedOrder();
    }

    @Test
    public void testAdd() {
        orderService.add(populatedOrder);
        verify(orderRepository, Mockito.times(1)).saveAndRefresh(populatedOrder);
        given(orderRepository.findById(populatedOrder.getOrderId())).willReturn(Optional.of(populatedOrder));
        Assert.assertEquals(populatedOrder, orderService.getById(populatedOrder.getOrderId()).get());
    }


    @Test
    public void testUpdate() {
        Order orderToUpdate = getPopulatedOrder();
        given(orderRepository.findById(orderToUpdate.getOrderId())).willReturn(Optional.of(orderToUpdate));
        orderService.update(orderToUpdate, orderToUpdate.getOrderId());
        verify(orderRepository, Mockito.times(1)).saveAndFlush(orderToUpdate);
    }

    @Test
    public void testDelete() {
        given(orderRepository.findById(populatedOrder.getOrderId())).willReturn(Optional.of(populatedOrder));
        orderService.delete(populatedOrder.getOrderId());
        verify(orderRepository, Mockito.times(1)).delete(populatedOrder);
    }

    @Test
    public void testGetAll() {
        given(orderRepository.findAll()).willReturn(List.of(populatedOrder));
        Assert.assertTrue(orderService.getAll().containsAll(List.of(populatedOrder)));
    }

    @Test
    public void testGetById() {
        given(orderRepository.findById(populatedOrder.getOrderId())).willReturn(Optional.of(populatedOrder));
        Assert.assertEquals(populatedOrder, orderService.getById(populatedOrder.getOrderId()).get());
    }

    public static Order getPopulatedOrder() {
        Set<OrderItem> orderItems = Set.of(new OrderItem(PRODUCT_1, 115), new OrderItem(PRODUCT_2, 116));
        return new Order(1L,"clientName1", LocalDateTime.now(), "London 129X6Z", orderItems);
    }
}