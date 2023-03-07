package com.guineeco.orderservice.controller;

import com.guineeco.orderservice.entities.OrderLineItems;
import com.guineeco.orderservice.models.OrderLineModel;
import com.guineeco.orderservice.services.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/order/")
@RequiredArgsConstructor
public class ControllerOrder {

    private final IOrderService orderService;
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody List<OrderLineModel> listOrder)
    {
         orderService.placeOrder(listOrder);
         return "order placed successfully";
    }

}
