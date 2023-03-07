package com.guineeco.serviceinventory.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.guineeco.serviceinventory.entities.Inventory;
import com.guineeco.serviceinventory.models.InventoryModel;
import com.guineeco.serviceinventory.repostiroy.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements IInventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public List<InventoryModel> checkIfisInStock(List<String> listSkuCode) {
        List<Inventory> listInventories = inventoryRepository.findBySkuCodeIn(listSkuCode);
        return listInventories.stream().map(this::mapToInvModel).toList();

    }

    private InventoryModel mapToInvModel(Inventory inv) {
        return InventoryModel.builder()
                .skuCode(inv.getSkuCode())
                .isAvailable(inv.getQuantity() == 0 ? false : true)
                .build();
    }
}
