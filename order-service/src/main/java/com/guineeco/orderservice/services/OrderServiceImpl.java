package com.guineeco.orderservice.services;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.guineeco.orderservice.entities.Order;
import com.guineeco.orderservice.entities.OrderLineItems;
import com.guineeco.orderservice.models.InventoryModel;
import com.guineeco.orderservice.models.OrderLineModel;
import com.guineeco.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
  private final OrderRepository orderRepository;
  private final WebClient.Builder webClientBuilder;

  @Override
  public void placeOrder(List<OrderLineModel> orderLineModels) {
    Order order = new Order();
    List<String> listSkuCode = orderLineModels.stream().map(OrderLineModel::getSkuCode).toList();
    // check if in stock

    InventoryModel[] productInStcoks = webClientBuilder.build().get().uri("http://inventory-service", uribuilder -> uribuilder
        .path("/api/inventory/")
        .queryParam("skucode", listSkuCode)
        .build())
        .retrieve()
        .bodyToMono(InventoryModel[].class)
        .block();

    for (InventoryModel inventoryModel : productInStcoks) {

      Predicate<OrderLineModel> predicate = p -> p.getSkuCode()
          .equalsIgnoreCase(inventoryModel.getSkuCode())
          &&
          Boolean.FALSE.equals(inventoryModel.getIsAvailable());

      orderLineModels
          .removeIf(predicate);

    }

    if (Boolean.FALSE.equals(orderLineModels.isEmpty())) {
      order.setOrderNumber(UUID.randomUUID().toString());
      List<OrderLineItems> listOrderLineItems = orderLineModels.stream().map(this::mapToOrder)
          .collect(Collectors.toList());
      order.setOrderLineItems(listOrderLineItems);

      orderRepository.save(order);
    }
    else {
      throw new IllegalArgumentException("none of the product is on stock ");
    }
  

  }

  private OrderLineItems mapToOrder(OrderLineModel orderLineModel) {

    return OrderLineItems.builder()
        .price(orderLineModel.getPrice())
        .skuCode(orderLineModel.getSkuCode())
        .quantity(orderLineModel.getQuantity())
        .build();

  }
}
