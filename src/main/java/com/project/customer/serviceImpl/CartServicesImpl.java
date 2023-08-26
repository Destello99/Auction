package com.project.customer.serviceImpl;

import com.project.customer.custome_exception.NoSuchResourceFound;
import com.project.customer.dto.CartDto;
import com.project.customer.dto.CartItemDto;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

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

        if(product.isStatus()){
            throw new NoSuchResourceFound("Product is not available");
        }
        //creating the cartItem to add it in the cart


        // Operation on cart
        Cart cart = customer.getCart();
        // //Cart cart1 = cartRepository.findByCustomerId(id).orElseThrow(()-> new NoSuchResourceFound("Cart is not allocated for customer"));
        // // setting a cart to perticular cart item
        if(cart==null){
        cart  = new Cart();
            customer.addCart(cart);
            cartRepository.save(cart);
        }

        System.out.println("before set "+cart.getId());
        CartItem cartItem = new CartItem();
        product.setStatus(true);
        cartItem.setProduct(product);
        System.out.println(cartItem.getProduct().getId());
        cartItem.setQuantity(quantity);
        double totalPrice = (product.getPrice()*quantity);
        System.out.println(quantity+" customerId"+customer.getId()+" total_price"+totalPrice);
        cartItem.setTotalPrice(totalPrice);
        //setting total price of cart

        double sum=0;
        Iterator <CartItem>itr=cart.getItems().iterator();

        while(itr.hasNext()){
            CartItem c1 = itr.next();
            sum = sum+c1.getTotalPrice();
        }
        cart.setTotalCartPrice(sum);
        cartItem.setCart(cart);

        System.out.println("cart item"+cartItem.getCart().getId());

        cart.addItems(cartItem);
        cart.getItems().add(cartItem);



        System.out.println("after set"+cart.getId());
        System.out.println(""+cartItem.getQuantity());
        System.out.println(""+cartItem.getTotalPrice());
        System.out.println(""+cartItem.getId());
        System.out.println(""+cartItem.getCart().getTotalCartPrice());
        System.out.println(""+cartItem.getCart().getCustomer());
        System.out.println(""+cartItem.getQuantity());

        cartItemRepository.save(cartItem);

        //setting total price of cart
        //
        cart.setTotalCartPrice(cart.getTotalCartPrice()+totalPrice);

        //CartDto cartDto = new CartDto();
        // mapper.map(cart,cartDto); //??? CartDto cartDto = mapper.map(cart, cartDto.class);

        System.out.println("Cart item added successfully");

        //after trying so much
//        Cart cart = customer.getCart();
//        if(cart == null){
//            cart = new Cart();
//        }
//
//        cartItem.setCart(cart);
//        Set<CartItem> items = cart.getItems();
//        //if product is already available in the items set then only increase the quantity of product //1.07
//        Iterator<CartItem> iterator = items.iterator();
//        Set<CartItem> newCartItems = new HashSet<>();
//        boolean flag = false;
//        while(iterator.hasNext()){
//            CartItem i = iterator.next();
//            if(i.getProduct().getId()==product_id){
//                i.setQuantity(quantity);
//                i.setTotalPrice(totalPrice);
//                flag = true;
//            }
//            newCartItems.add(i);
//        }
//
//        if(flag == true){
//            items.clear();
//            items.addAll(newCartItems);
//        }else{
//            //if product is not available in the items
//            cartItem.setCart(cart);
//            items.add(cartItem);
//        }
//
//        Cart savedCart = cartRepository.save(cart);
        CartDto cartDto = new CartDto();
//        CartItemDto cartItemDto = new CartItemDto();
//        return mapper.map(cart,CartDto.class);
        cartDto.setTotalCartPrice(cart.getTotalCartPrice());
        Set<CartItemDto> cartItemList = cart.getItems().stream().map(item -> mapper.map(item, CartItemDto.class)).collect(Collectors.toSet());
        cartDto.setItems(cartItemList);
//        cartDto.getItems().forEach(item -> System.out.println(item.getProduct().getId()));
        System.out.println("dto mapped");
        return cartDto;
    }



    //method to get the cart details with items in the cart
    @Override
    public CartDto getCartDetails(Integer userId) {
        Customer customer = customerRepository.findById(userId).orElseThrow(()->new NoSuchResourceFound("Customer not found"));
        Cart cart = customer.getCart();

        Cart cart1 = cartRepository.findByCustomerId(userId).orElseThrow(()->new NoSuchResourceFound("Cart not found"));


        Set<CartItem> items = cart.getItems();
        CartDto cartDto = new CartDto();

        //mapper.map(cart,cartDto);
        return mapper.map(cart,CartDto.class);
    }

    @Override
    public CartDto removeItem(Integer userId, Integer productId) {
        Customer customer = customerRepository.findById(userId).orElseThrow(()->new NoSuchResourceFound("Customer not found"));
        Cart cart = customer.getCart();
        Set<CartItem> items = cart.getItems();
//        Product product = productRepository.findById(productId).orElseThrow(()->new NoSuchResourceFound("Product Not found"));

//        items.stream().filter((p)->p.getProduct().getId()==productId).collect()
        Iterator<CartItem> itemIterator = items.iterator();
        boolean flag = false;
        //find item to remove
        CartItem cartItemToRemove = new CartItem();
        while (itemIterator.hasNext()){
            CartItem cartItem = itemIterator.next();
            if(cartItem.getProduct().getId()==productId){
                flag = true;
               cartItemToRemove = cartItem;
            }
        }

        if(flag == true){
            Product product = cartItemToRemove.getProduct();
            product.setStatus(false);
            cart.setTotalCartPrice(cart.getTotalCartPrice()-product.getPrice());
        }

        cart.removeItems(cartItemToRemove);

        CartDto cartDto = new CartDto();

        return mapper.map(cart,CartDto.class);
    }

    @Override
    public CartDto deleteAllItems(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new NoSuchResourceFound("Cart is not allocated to customer"));
        Cart cart = customer.getCart();
        Set<CartItem> cartItems =cart.getItems();
        System.out.println(cartItems.toString());
        System.out.println("Inside the deletecart");

        if (!cartItems.isEmpty()) {
            for (CartItem item : cartItems) {
                item.getProduct().setStatus(false);
                item.setCart(null);
                System.out.println(cart.toString());
            }
            cartItems.clear();
            cart.setTotalCartPrice(0.0);
        }

        CartDto cartDto = new CartDto();

        return mapper.map(cart,CartDto.class);
    }

    //method to remove item from the cart

}
