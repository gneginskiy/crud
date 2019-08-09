package com.tradeservice.repository.product;

import com.tradeservice.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureMockMvc
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void before() {
        productRepository.deleteAll();
    }

    @Test
    public void testSave() throws Exception {
        Product newProduct1 = populateProduct();
        Assert.assertNotNull(productRepository.findAll().stream().filter(p -> p.equals(newProduct1)).findAny().orElse(null));
    }

    @Test
    public void testFindAllByIds() throws Exception {
        List<Product> productsToSave = LongStream.iterate(1, i -> ++i).limit(50)
                .mapToObj(i -> new Product(i + "Товар #12315" + i, 59.0 + i))
                .collect(toList());
        productRepository.saveAll(productsToSave);

        List<Product> productsFound = productRepository.findAllById(productsToSave.stream().map(Product::getProductId).collect(toList()));
        Assert.assertEquals(productsFound, productsToSave);
    }

    @Test
    public void testDelete() throws Exception {
        Product newProduct1 = populateProduct();
        productRepository.delete(newProduct1);
        Assert.assertEquals(0, productRepository.findAll().size());
    }

    private Product populateProduct() {
        Product newProduct1 = new Product("Товар #12315", 59.0);
        productRepository.save(newProduct1);
        return newProduct1;
    }

    @Test
    public void testUpdate() throws Exception {
        Product newProduct1 = populateProduct();
        String newName = "Новое имя!";
        newProduct1.setName(newName);
        productRepository.save(newProduct1);
        Assert.assertEquals(productRepository.findById(newProduct1.getProductId()).get().getName(), newName);
    }
}
