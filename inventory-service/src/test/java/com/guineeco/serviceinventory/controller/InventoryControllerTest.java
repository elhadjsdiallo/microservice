package com.guineeco.serviceinventory.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private List<String> listSkuCode;
    

    @BeforeEach
    void setup() {

        listSkuCode = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listSkuCode.add("iphone_" + i);
        }
    }

    @Test
    @DisplayName("Test if product is available on stocks")
    void shoudlcheckifProductsAreAvailable() throws Exception {

        MultiValueMap<String,String>params=new LinkedMultiValueMap<>();
        for (String code : listSkuCode) {
            params.add("skucode", code);
        }
        
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/inventory/")
                        .params(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].isAvailable").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(5));
            

    }
}
