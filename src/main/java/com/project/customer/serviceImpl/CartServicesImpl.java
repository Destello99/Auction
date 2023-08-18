package com.project.customer.serviceImpl;

import com.project.customer.custome_exception.NoSuchResourceFound;
import com.project.customer.dto.CartDto;
import com.project.customer.entity.Cart;
import com.project.customer.entity.CartItem;
import com.project.customer.entity.Customer;
import com.project.customer.entity.Product;
import com.project.customer.repositories.CartItemRepository;
import com.project.customer.repositories.CartRepository;
import com.project.customer.repositories.CustomerRepository;
import com.project.customer.repositories.ProductRepository;
import com.project.customer.service.CartServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.Set;

@Service
@Transactional
public class CartServicesImpl implements CartServices {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper mapper;

    public CartDto addItem(int quantity,Integer product_id,Integer id){

        Customer customer = customerRepository.findById(id).orElseThrow();
        Product product = productRepository.findById(product_id).orElseThrow(()->new NoSuchResourceFound("No such Product Avialable"));

        System.out.println(customer.getId());

        if(!product.isStatus()){
            throw new NoSuchResourceFound("Product is not available");
        }
        //creating the cartItem to add it in the cart
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        System.out.println(cartItem.getProduct().getId());
        cartItem.setQuantity(quantity);
        double totalPrice = (product.getPrice()*quantity);
        cartItem.setTotalPrice(totalPrice);

        // Operation on cart
        Cart cart = customer.getCart();
        //Cart cart1 = cartRepository.findByCustomerId(id).orElseThrow(()-> new NoSuchResourceFound("Cart is not allocated for customer"));
        // setting a cart to perticular cart item
        if(cart==null){
        cart  = new Cart();
            customer.addCart(cart);
            cartRepository.save(cart);
        }

        System.out.println(cart.getId());
        cartItem.setCart(cart);

        cart.getItems().add(cartItem);
        cartItemRepository.save(cartItem);

        //setting total price of cart
        cart.setTotalCartPrice(cart.getTotalCartPrice()+totalPrice);

        CartDto cartDto = new CartDto();
        // mapper.map(cart,cartDto); //??? CartDto cartDto = mapper.map(cart, cartDto.class);
        return mapper.map(cart,CartDto.class);
    }

    //method to get the cart details with items in the cart
    @Override
    public CartDto getCartDetails(Integer userId) {
        Customer customer = customerRepository.findById(userId).orElseThrow(()->new NoSuchResourceFound("Customer not found"));
        Cart cart = customer.getCart();

        Cart cart1 = cartRepository.findByCustomerId(userId).orElseThrow(()->new NoSuchResourceFound("Cart not found"));


        Set<CartItem> items = cart.getItems();
        CartDto cartDto = new CartDto();

        mapper.map(cart,cartDto);
        return cartDto;
    }

    @Override
    public CartDto removeItem(Integer userId, Integer productId) {
        Customer customer = customerRepository.findById(userId).orElseThrow(()->new NoSuchResourceFound("Customer not found"));
        Cart cart = customer.getCart();
        Set<CartItem> items = cart.getItems();
//        Product product = productRepository.findById(productId).orElseThrow(()->new NoSuchResourceFound("Product Not found"));

//        items.stream().filter((p)->p.getProduct().getId()==productId).collect()
        Iterator<CartItem> itemIterator = items.iterator();

        //find item to remove
        CartItem cartItemToRemove = new CartItem();
        while (itemIterator.hasNext()){
            CartItem cartItem = itemIterator.next();
            if(cartItem.getProduct().getId()==productId){
               cartItemToRemove = cartItem;
            }
        }

        cart.removeItems(cartItemToRemove);

        CartDto cartDto = new CartDto();
        mapper.map(cart,cartDto);
        return null;
    }

    //method to remove item from the cart






}
