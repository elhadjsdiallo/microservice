package com.guineeco.serviceinventory.repostiroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guineeco.serviceinventory.entities.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {


   List<Inventory> findBySkuCodeIn(List<String>listSkucode);
    
}
