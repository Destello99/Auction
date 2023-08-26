package com.project.customer.controllers;

import com.project.customer.serviceImpl.InventoryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

    private InventoryService inventoryService;

    @PutMapping("updatePrice/{id}/{price}")
    public String updatePrice(@PathVariable int id,@PathVariable int price){

        inventoryService.incrementProductAmount(id,price);

        return "Product value updated  successfully";

    }
}
