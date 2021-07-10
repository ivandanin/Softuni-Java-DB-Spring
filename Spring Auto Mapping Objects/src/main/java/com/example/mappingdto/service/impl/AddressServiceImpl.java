package com.example.mappingdto.service.impl;

import com.example.mappingdto.entities.Address;
import com.example.mappingdto.repos.AddressRepo;
import com.example.mappingdto.service.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    private AddressRepo addressRepo;

    public AddressServiceImpl(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepo.findAll();
    }

    @Override
    public Address getAddressById(Long id) throws Exception {
        return addressRepo.findById(id).orElseThrow(() ->
        new Exception(String.format("Address with ID = %s does not exists.", id)));
    }

    @Override
    public Long getAddressCount() {
        return addressRepo.count();
    }

    @Override
    public Address addAddress(Address address) {
        address.setId(null);
        return addressRepo.save(address);
    }

    @Override
    public Address updateAddress(Address address) throws Exception {
        getAddressById(address.getId());
        return addressRepo.save(address);
    }

    @Override
    public Address deleteAddress(Long id) throws Exception {
        Address removed = getAddressById(id);
        addressRepo.deleteById(id);
        return removed;
    }
}
