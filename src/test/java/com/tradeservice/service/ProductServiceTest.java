package com.tradeservice.service;

import com.tradeservice.repository.product.ProductRepository;
import com.tradeservice.service.impl.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static com.tradeservice.util.TestConstants.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    ProductService    productService;
    ProductRepository productRepository;

    @Before
    public void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void testGetAll() {
        given(productRepository.findAll()).willReturn(PRODUCT_LIST);
        Assert.assertTrue(productService.getAll().containsAll(PRODUCT_LIST));
    }

    @Test
    public void testGetById() {
        given(productRepository.findById(PRODUCT_1.getProductId())).willReturn(Optional.of(PRODUCT_1));
        Assert.assertEquals(PRODUCT_1, productService.getById(PRODUCT_1.getProductId()).get());
    }

    @Test
    public void testDelete() {
        given(productRepository.findById(PRODUCT_1.getProductId())).willReturn(Optional.of(PRODUCT_1));
        productService.delete(PRODUCT_1.getProductId());
        verify(productRepository, Mockito.times(1)).delete(PRODUCT_1);
    }

    @Test
    public void testAdd() {
        productService.add(PRODUCT_1);
        verify(productRepository, Mockito.times(1)).save(PRODUCT_1);
        given(productRepository.findById(PRODUCT_1.getProductId())).willReturn(Optional.of(PRODUCT_1));
        Assert.assertEquals(PRODUCT_1, productService.getById(PRODUCT_1.getProductId()).get());
    }

    @Test
    public void testUpdate() {
        given(productRepository.findById(PRODUCT_1.getProductId())).willReturn(Optional.of(PRODUCT_1));
        productService.update(PRODUCT_1, PRODUCT_1.getProductId());
        verify(productRepository, Mockito.times(1)).save(PRODUCT_1);
    }
}