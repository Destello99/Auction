package com.project.customer.serviceImpl;

import com.project.customer.entity.Address;
import com.project.customer.repositories.AddressRepository;
import com.project.customer.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Override
    public List<Address> allAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }
}
