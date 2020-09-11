package com.example.demo.democs.services;


import com.example.demo.democs.web.dto.CustomerDto;
import com.example.demo.democs.web.dto.CustomerSearchDto;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    CustomerDto getCustomer(UUID uuid);
    List<CustomerDto> searchCustomer(CustomerSearchDto searchRequest);
    CustomerDto saveCustomer(CustomerDto customer);
    CustomerDto updateCustomer(CustomerDto customer);



}
