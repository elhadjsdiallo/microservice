package com.guineeco.serviceinventory.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InventoryModel {

    private String skuCode;
    private Boolean isAvailable;
    

}
