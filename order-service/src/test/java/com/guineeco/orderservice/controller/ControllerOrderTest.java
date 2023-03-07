package com.guineeco.orderservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guineeco.orderservice.models.OrderLineModel;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ControllerOrderTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper=new ObjectMapper();
    private List<OrderLineModel>listOrders=new ArrayList<>();
    @BeforeEach
    void setup()
    {
        for (int i = 0; i <5; i++) {

            listOrders.add(OrderLineModel.builder()
                            .price(BigDecimal.valueOf(1500))
                            .skuCode("iphone_"+i)
                            .quantity(i)
                    .build());
        }

    }
    @Test
    @DisplayName("Test placing an order with list of order Line items")
    void ShouldPlaceOrderBasedOnListOrderLine() throws Exception {
        String body=objectMapper.writeValueAsString(listOrders);
       MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post("/api/order/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

       String response=mvcResult.getResponse().getContentAsString();
       assertEquals("order placed successfully",response);
    }

}