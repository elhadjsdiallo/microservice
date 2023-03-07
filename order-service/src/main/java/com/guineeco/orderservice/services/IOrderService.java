package com.guineeco.orderservice.services;

import com.guineeco.orderservice.models.OrderLineModel;

import java.util.List;

public interface IOrderService {
    void placeOrder(List<OrderLineModel> orderLineModels);

}
