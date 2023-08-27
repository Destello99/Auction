//package com.project.customer.serviceImpl;
//
//import com.project.customer.entity.Product;
//import com.project.customer.repositories.ProductRepository;
//import com.project.customer.service.AuctionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class AuctionServiceImpl implements AuctionService {
//        @Autowired
//        private ProductRepository productRepository;
//        @Override
//        public void startAuction() {
//            List<Product> products = productRepository.findAll();
//            for (Product product : products) {
//                if (product.isStatus() && product.getBidEndTime().isBefore(LocalDateTime.now())) {
//                    if (product.getHighestBidder() != null) {
//                        product.setHighestBidder(product.getHighestBidder());
//                        product.setStatus(false);
//                    } else {
//                        product.setStatus(false);
//                    }
//                    productRepository.save(product);
//                }
//            }
//        }
//}
