package com.tradeservice.repository.product;

import com.tradeservice.entity.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.tradeservice.util.TestConstants.PRODUCT_LIST;
import static java.util.stream.Collectors.toList;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureMockMvc
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @After
    public void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    public void testSave() {
        Product newProduct1 = populateProduct();
        Assert.assertNotNull(productRepository.findAll().stream().filter(p -> p.equals(newProduct1)).findAny().orElse(null));
    }

    @Test
    public void testFindAllByIds() {
        List<Product> products = productRepository.saveAll(PRODUCT_LIST);

        List<Product> productsFound = productRepository.findAllById(products.stream().map(Product::getProductId).collect(toList()));
        Assert.assertEquals(productsFound, products);
    }

    @Test
    public void testDelete() {
        Product newProduct1 = populateProduct();
        productRepository.delete(newProduct1);
        Assert.assertEquals(0, productRepository.findAll().size());
    }

    private Product populateProduct() {
        Product newProduct1 = new Product("Product #12315", 59.0);
        productRepository.save(newProduct1);
        return newProduct1;
    }

    @Test
    public void testUpdate() {
        Product newProduct1 = populateProduct();
        String newName = "Новое имя!";
        newProduct1.setName(newName);
        productRepository.save(newProduct1);
        Assert.assertEquals(productRepository.findById(newProduct1.getProductId()).get().getName(), newName);
    }
}
