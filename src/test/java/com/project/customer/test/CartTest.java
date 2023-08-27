package com.project.customer.test;

import com.project.customer.custome_exception.NoSuchResourceFound;
import com.project.customer.entity.Cart;
import com.project.customer.entity.CartItem;
import com.project.customer.entity.Customer;
import com.project.customer.repositories.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CartTest {
    @Autowired
    private CartRepository cartRepository;

    @Test
    void testGetCartDetail(){
        Cart cart1 = cartRepository.findByCustomerId(4).orElseThrow(()->new NoSuchResourceFound("Cart not found"));
        Set<CartItem> cartItems= cart1.getItems();
//        System.out.println(cartItems.size());
        assertEquals(114,cart1.getTotalCartPrice());
    }

    @Test
    void testRemoveItem(){

    }
}
