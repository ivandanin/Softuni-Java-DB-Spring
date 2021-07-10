package com.example.mappingdto.service.impl;

import com.example.mappingdto.entities.Address;
import com.example.mappingdto.repos.AddressRepo;
import com.example.mappingdto.service.AddressService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
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
    @Transactional
    public Address addAddress(Address address) {
        address.setId(null);
        return addressRepo.save(address);
    }

    @Override
    @Transactional
    public Address updateAddress(Address address) throws Exception {
        getAddressById(address.getId());
        return addressRepo.save(address);
    }

    @Override
    @Transactional
    public Address deleteAddress(Long id) throws Exception {
        Address removed = getAddressById(id);
        addressRepo.deleteById(id);
        return removed;
    }
}
