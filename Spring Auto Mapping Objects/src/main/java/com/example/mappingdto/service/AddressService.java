package com.example.mappingdto.service;

import com.example.mappingdto.entities.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddresses();
    Address getAddressById(Long id) throws Exception;
    Long getAddressCount();
    Address addAddress(Address address);
    Address updateAddress(Address address) throws Exception;
    Address deleteAddress(Long id) throws Exception;
}
