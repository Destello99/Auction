package com.project.customer.service;

import com.project.customer.dto.CartDto;
import com.project.customer.entity.Cart;

public interface CartServices {

    //method to add items in the cart
//     CartDto addItem(int quantity, Integer product_id, Integer id);
    CartDto addItem(int quantity, Integer product_id, Integer id);
     //Method to get cart details with the items in the cart
    CartDto getCartDetails(Integer userId);

    //Method to remove item from the cart
    CartDto removeItem(Integer userId,Integer productId);

    CartDto deleteAllItems(Integer customerId);
}
