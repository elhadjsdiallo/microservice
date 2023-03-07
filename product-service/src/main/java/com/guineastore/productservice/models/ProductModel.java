package com.guineastore.productservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductModel implements Serializable {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}
