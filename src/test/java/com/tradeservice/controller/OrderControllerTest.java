package com.tradeservice.controller;

import com.tradeservice.entity.Order;
import com.tradeservice.service.impl.OrderServiceImpl;

import static com.tradeservice.util.TestUtils.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderServiceImpl orderService;

    @Test
    public void testGetAll() throws Exception {
        List<Order> ordersList = List.of(POPULATED_ORDER);
        when(orderService.getAll()).thenReturn(ordersList);
        this.mockMvc.perform(get("/orders").contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(POPULATED_ORDERS_ARRAY_JSON));
        verify(orderService, times(1)).getAll();
    }

    @Test
    public void testGetById() throws Exception {
        Order order = POPULATED_ORDER;
        when(orderService.getById(order.getOrderId())).thenReturn(Optional.of(order));
        this.mockMvc.perform(get("/orders/"+order.getOrderId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(POPULATED_ORDER_JSON));
        verify(orderService, times(1)).getById(order.getOrderId());
    }

    @Test
    public void testDelete() throws Exception {
        when(orderService.getById(POPULATED_ORDER.getOrderId())).thenReturn(Optional.of(POPULATED_ORDER));
        this.mockMvc.perform(delete("/orders/"+POPULATED_ORDER.getOrderId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isAccepted());
        verify(orderService, times(1)).delete(POPULATED_ORDER.getOrderId());
    }

    @Test
    public void testAdd() throws Exception {
        this.mockMvc.perform(post("/orders")
                .content(POPULATED_ORDER_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated());
        verify(orderService, times(1)).add(POPULATED_ORDER);
    }

    @Test
    public void testUpdate() throws Exception {
        this.mockMvc.perform(put("/orders/" + POPULATED_ORDER.getOrderId())
                .content(POPULATED_ORDER_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isAccepted());
        verify(orderService, times(1)).update(POPULATED_ORDER, POPULATED_ORDER.getOrderId());
    }
}