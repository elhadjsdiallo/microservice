package com.guineeco.orderservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InventoryModel {

    private String skuCode;
    private Boolean isAvailable;
    
    
}
