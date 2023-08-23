package com.project.customer.service;

import com.project.customer.entity.Address;
import java.util.List;


public interface AddressService {
    List<Address> allAddress();
    Address addAddress(Address address);
}
