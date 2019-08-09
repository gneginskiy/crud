package com.tradeservice.service;

import com.tradeservice.entity.Product;
import com.tradeservice.repository.product.ProductRepository;
import com.tradeservice.service.impl.ProductServiceImpl;
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
public class ProductServiceTest {

    private static final Product PRODUCT_1 = new Product(1L, "Товар #12311", 51.0);
    private static final Product PRODUCT_2 = new Product(2L, "Товар #12312", 52.0);
    private static final Product PRODUCT_3 = new Product(3L, "Товар #12313", 53.0);
    private static final List<Product> PRODUCT_LIST = List.of(PRODUCT_1, PRODUCT_2, PRODUCT_3);

    ProductService productService;
    ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
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