package com.project.customer.serviceImpl;
import com.project.customer.entity.Item;
import com.project.customer.repositories.ItemRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;// impl ctor based D.I achieved via parameterized ctor generate thanks
    // to @RequiredArgsConstructor

    @Transactional( propagation = Propagation.REQUIRES_NEW)
    public void incrementAmount(int id, int amount) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        item.setPrice(item.getPrice() + amount);
    }

}
