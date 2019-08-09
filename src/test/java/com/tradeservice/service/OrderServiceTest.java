package com.tradeservice.service;

import com.tradeservice.entity.Product;
import com.tradeservice.repository.product.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OrderServiceTest {

    @Autowired private ProductRepository     productRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void before(){
        productRepository.deleteAll();
    }

    @Test
    public void testSave() throws Exception {
        Product newProduct1 = new Product("Товар #12315", 59.0);
        productRepository.save(newProduct1);
        Assert.assertNotNull(productRepository.findAll().stream().filter(p->p.equals(newProduct1)).findAny().orElse(null));
    }

    @Test
    public void testFindByIds() throws Exception {
        List<Product> productsToSave = LongStream.iterate(1, i -> ++i).limit(50)
                .mapToObj(i -> new Product(i+"Товар #12315" + i, 59.0 + i))
                .collect(toList());
        productRepository.saveAll(productsToSave);

        List<Product> productsFound = productRepository.findAllById(productsToSave.stream().map(Product::getProductId).collect(toList()));
        Assert.assertEquals(productsFound,productsToSave);
    }





    //    @Autowired private WebApplicationContext webApplicationContext;

//    @Before
//    public void before(){
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
//    }


//        // request get
//        mockMvc.perform(
//                get("/user/login")
//                        .param("email", email)
//                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$.message").value("user exist"));

    //    @Test
//    public void test_user_not_exist() throws Exception {
//        // parameter
//        String email = "test@test.com";
//
//        // request get
//        mockMvc.perform(
//                get("/user/login")
//                        .param("email", email)
//                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andDo(print())
//                .andExpect(status().is5xxServerError())
//                .andExpect(jsonPath("$.message").value("user is not exist"));
//    }

}
