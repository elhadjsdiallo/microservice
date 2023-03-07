package com.guineeco.serviceinventory.commandline;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.guineeco.serviceinventory.entities.Inventory;
import com.guineeco.serviceinventory.repostiroy.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TaskRunner implements CommandLineRunner {

    private final InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {

        if(inventoryRepository.findAll().isEmpty())
        {
            List<Inventory> listInventories = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
    
                listInventories.add(Inventory.builder()
                        .quantity( i==1 ? 0:20)
                        .skuCode("iphone_" + i)
                        .build());
            }
    
            inventoryRepository.saveAll(listInventories);
        }
    

    }

}
