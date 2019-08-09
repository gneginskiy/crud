package com.tradeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeservice.service.impl.ProductServiceImpl;

import static com.tradeservice.util.TestConstants.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productService;

    @Test
    public void testGetAll() throws Exception {
        when(productService.getAll()).thenReturn(PRODUCT_LIST);
        this.mockMvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(asJsonString(PRODUCT_LIST)));
    }

    @Test
    public void testGetById() throws Exception {
        when(productService.getById(PRODUCT_1.getProductId())).thenReturn(Optional.of(PRODUCT_1));
        this.mockMvc.perform(get("/products/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(asJsonString(PRODUCT_1)));
    }

    @Test
    public void testDelete() throws Exception {
        this.mockMvc.perform(delete("/products/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isAccepted());
        verify(productService, times(1)).delete(PRODUCT_1.getProductId());
    }

    @Test
    public void testAdd() throws Exception {
        this.mockMvc.perform(post("/products")
                .content(asJsonString(PRODUCT_1))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated());
        verify(productService, times(1)).add(PRODUCT_1);
    }

    @Test
    public void testUpdate() throws Exception {
        this.mockMvc.perform(put("/products/" + PRODUCT_1.getProductId())
                .content(asJsonString(PRODUCT_1))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isAccepted());
        verify(productService, times(1)).update(PRODUCT_1, PRODUCT_1.getProductId());
    }


    @SneakyThrows
    private static String asJsonString(final Object obj) {
        return new ObjectMapper().writeValueAsString(obj);
    }

}