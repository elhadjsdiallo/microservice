package com.guineeco.orderservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Entity
@Table(name = "order_lines_items")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderLineItems implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
