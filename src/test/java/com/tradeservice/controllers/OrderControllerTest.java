package com.tradeservice.controllers;

import com.tradeservice.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

//@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class OrderControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean

  private OrderController orderController;

  // TODO без этого errors  UnsatisfiedDependencyException: Error creating bean with name 'orderAPI'  defined in file

  @BeforeEach
  void setUp() {
    //Goods goods1 = new Goods(1L, "ТОВАР 1", 11D);
    //Goods goods2 = new Goods(2L, "ТОВАР 2", 22D);
    //Goods goods3 = new Goods(3L, "ТОВАР 3", 33D);
    //Goods goods4 = new Goods(4L, "ТОВАР 4", 44D);
  }


  //
  @Test
  void addGoods() throws Exception {
//    Goods goods1 = new Goods(1L, "ТОВАР 1", 11D);
//
////    BDDMockito.given(goodsService.add(goods1)).willReturn(goods1);
//
//    this.mockMvc.perform(MockMvcRequestBuilders.post("/goods", goods1))
//        .andExpect(MockMvcResultMatchers.status().isCreated())
//        .andExpect(content().
//            json("{'goodsId': 1, 'name': 'ТОВАР 1', 'price': 11}"));
  }

  @Test
  void editGoods() {
  }

  @Test
  void deleteGoods() {
  }

  @Test
  void getAllOrders() throws Exception {
    ResultMatcher data = content().json("[\n" +
            "    {\n" +
            "        \"orderId\": 1,\n" +
            "        \"clientName\": \"Клиент 1\",\n" +
            "        \"date\": \"2019-08-08T21:18:57.333+0000\",\n" +
            "        \"address\": \"Адрес 1\",\n" +
            "        \"orderItems\": [\n" +
            "            {\n" +
            "                \"id\": 2,\n" +
            "                \"goods\": {\n" +
            "                    \"goodsId\": 2,\n" +
            "                    \"name\": \"Товар 2\",\n" +
            "                    \"price\": 999.0\n" +
            "                },\n" +
            "                \"count\": 12\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 1,\n" +
            "                \"goods\": {\n" +
            "                    \"goodsId\": 1,\n" +
            "                    \"name\": \"Товар 1\",\n" +
            "                    \"price\": 59.0\n" +
            "                },\n" +
            "                \"count\": 11\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"orderId\": 2,\n" +
            "        \"clientName\": \"Клиент 2\",\n" +
            "        \"date\": \"2019-08-08T21:18:57.333+0000\",\n" +
            "        \"address\": \"Адрес 2\",\n" +
            "        \"orderItems\": [\n" +
            "            {\n" +
            "                \"id\": 3,\n" +
            "                \"goods\": {\n" +
            "                    \"goodsId\": 1,\n" +
            "                    \"name\": \"Товар 1\",\n" +
            "                    \"price\": 59.0\n" +
            "                },\n" +
            "                \"count\": 22\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 4,\n" +
            "                \"goods\": {\n" +
            "                    \"goodsId\": 2,\n" +
            "                    \"name\": \"Товар 2\",\n" +
            "                    \"price\": 999.0\n" +
            "                },\n" +
            "                \"count\": 23\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "]");

//    Goods goods = new Goods(3l,"Товар 1",59.0);

//    Order parentOrder = new Order(2l, "Клиент 2", Date.valueOf("2019-08-08T21:18:57.333+0000"), "Адрес 2",
//            Set.of(new OrderItem(goods, 2)));
//    given(orderApi.getAllOrders()).willReturn(ResponseEntity.ok(List.of(parentOrder)));
//    this.mockMvc.perform(MockMvcRequestBuilders.get("/orders"))
//                .andExpect(data);

  }

  @Test
  void getGoodsById() {
  }
}
