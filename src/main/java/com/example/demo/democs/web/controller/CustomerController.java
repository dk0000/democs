package com.example.demo.democs.web.controller;

import com.example.demo.democs.services.CustomerService;
import com.example.demo.democs.web.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers/{customerId}")
    public ResponseEntity getCustomerById(@PathVariable("customerId") UUID uuid){
        return new ResponseEntity(customerService.getCustomer(uuid), HttpStatus.OK);
    }

    @PostMapping("/customers/")
    public ResponseEntity createCustomer(@RequestBody CustomerDto customerDto){
        return new ResponseEntity(customerService.saveCustomer(customerDto), HttpStatus.CREATED);
    }



}
