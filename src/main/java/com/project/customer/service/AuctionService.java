package com.project.customer.service;

import com.project.customer.entity.Product;
import com.project.customer.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuctionService {

    @Autowired
    private ProductRepository productRepository;

    public void startAuction() {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            if (product.isStatus() && product.getBidEndTime().isBefore(LocalDateTime.now())) {
                // The auction has ended, determine the winner
                if (product.getHighestBidder() != null) {
                    // The product has a highest bidder, declare the winner
                    product.setHighestBidder(product.getHighestBidder());
                    product.setStatus(false);
                } else {
                    // No bidder, mark as inactive
                    product.setStatus(false);
                }
                productRepository.save(product);
            }
        }
    }


}