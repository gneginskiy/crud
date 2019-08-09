package com.tradeservice.repository.product;

import com.tradeservice.db.TestInitDbRunner;
import com.tradeservice.entity.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository     productRepository;

    private MockMvc mockMvc;

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

    }

    @BeforeEach
    public void before(){
        //productRepository.deleteAll();
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

    @Test
    public void testDelete() throws Exception {
        Product newProduct1 = new Product("Товар #12315", 59.0);
        productRepository.save(newProduct1);
        productRepository.delete(newProduct1);
        Assert.assertTrue(productRepository.findAll().size()==0);
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
