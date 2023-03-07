package com.guineeco.serviceinventory.services;

import java.util.List;

import com.guineeco.serviceinventory.models.InventoryModel;

public interface IInventoryService {


    List<InventoryModel> checkIfisInStock(List<String>listSkuCode);

    

}
