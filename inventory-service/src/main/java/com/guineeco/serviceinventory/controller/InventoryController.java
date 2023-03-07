package com.guineeco.serviceinventory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.guineeco.serviceinventory.models.InventoryModel;
import com.guineeco.serviceinventory.services.IInventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory/")
@RequiredArgsConstructor
public class InventoryController {

    private final IInventoryService inventoryService;

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public List<InventoryModel> isAvailable(@RequestParam(name = "skucode") List<String> listSkuCode) {

        return inventoryService.checkIfisInStock(listSkuCode);

    }
}
