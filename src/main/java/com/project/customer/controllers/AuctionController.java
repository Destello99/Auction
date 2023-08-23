package com.project.customer.controllers;

import com.project.customer.entity.BidAmount;
import com.project.customer.entity.Product;
import com.project.customer.repositories.ProductRepository;
import com.project.customer.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/{productId}/bid")
    public ResponseEntity<String> placeBid(@PathVariable Integer productId, @RequestBody BidAmount bidAmount) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (product.isStatus() && product.getBidEndTime().isAfter(LocalDateTime.now())) {
                if (product.getHighestBid() == 0 || bidAmount.getBidAmount() > product.getHighestBid()) {
                    product.setHighestBid(bidAmount.getBidAmount());
                    product.setHighestBidder(product.getHighestBidder()); // Set the highest bidder
                    // Reset the bid end time to give another 10 seconds
                    product.setBidEndTime(LocalDateTime.now().plusSeconds(10));
                    productRepository.save(product);
                    return ResponseEntity.ok("Bid placed successfully.");
                }
                return ResponseEntity.badRequest().body("Bid amount should be higher than the current highest bid.");
            }
        }
        return ResponseEntity.notFound().build();
    }

}
