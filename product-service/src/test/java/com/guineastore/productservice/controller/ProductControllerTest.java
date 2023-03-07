package com.guineastore.productservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guineastore.productservice.models.ProductModel;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"test"})
@RequiredArgsConstructor
class ProductControllerTest {
    @Autowired
    private  MockMvc mockMvc;


    private ProductModel givenProductModel;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        givenProductModel = ProductModel
                .builder()
                .name("iphone 12")
                .description("iphone 12")
                .price(BigDecimal.valueOf(1500)).build();

    }

    @Test
    @DisplayName("Test creating product")
    void testWhenWhenProductsIsCreated() throws Exception {

        mockMvc.perform(post("/api/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(givenProductModel))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("iphone 12"));


    }

    @Test
    @DisplayName("Test getting all products")
    void getAllProducts() throws Exception {
       ProductModel secondProduct = ProductModel
                .builder()
                .name("iphone 13")
                .description("iphone 13")
                .price(BigDecimal.valueOf(1500)).build();

        mockMvc.perform(post("/api/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(givenProductModel))
                )
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(secondProduct))
                )
                .andExpect(status().isCreated());

       mockMvc.perform(get("/api/product/"))
                .andExpect(status().isOk())
               .andExpect(jsonPath("$.length()").value(2));



    }
}