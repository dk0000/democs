package com.example.demo.democs.services;

import com.example.demo.democs.domain.Customer;
import com.example.demo.democs.domain.Product;
import com.example.demo.democs.exception.NotFoundException;
import com.example.demo.democs.repositories.CustomerRepository;
import com.example.demo.democs.web.dto.CustomerDto;
import com.example.demo.democs.web.dto.CustomerSearchDto;
import com.example.demo.democs.web.dto.DateTimeMapper;
import com.example.demo.democs.web.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final DateTimeMapper dateTimeMapper;

    @Override
    public CustomerDto getCustomer(UUID uuid) {
        return domainToDto(customerRepository.findById(uuid).orElseThrow(NotFoundException::new));
    }

    @Override
    public List<CustomerDto> searchCustomer(CustomerSearchDto searchDto) {

        List<Customer> customers = new ArrayList<>();
        // first search based on ssn and dob if present

        if (searchDto.getSsn() != null && searchDto.getDob() != null){
            customers = customerRepository.findByDobAndSsn(searchDto.getDob(), searchDto.getSsn());
        }else if (searchDto.getDob() != null &&
        searchDto.getFirstName() != null &&
        searchDto.getEmail() != null){
            customers = customerRepository.findByDobAndFirstNameAndEmail(
                    searchDto.getDob(),
                    searchDto.getFirstName(),
            searchDto.getEmail());

        }
        return customers.stream().map(aCustomer -> {
            return domainToDto(aCustomer);
        }).collect(Collectors.toList());
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customer) {
        return domainToDto(customerRepository.save(DtoToDomain(customer)));
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customer) {
        return domainToDto(customerRepository.save(DtoToDomain(customer)));
    }

    private Customer DtoToDomain(CustomerDto customerDto){
        return Customer.builder()
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .dob(customerDto.getDob())
                .email(customerDto.getEmail())
                .ssn(customerDto.getSsn())
                .id(customerDto.getId())
                .products(
                        customerDto.getProducts().stream().map(
                                aProduct -> {
                                    return Product.builder()
                                            .application_id(aProduct.getApplication_id())
                                            .business_id(aProduct.getBusiness_id())
                                            .cerebro_pid(aProduct.getCerebro_pid())
                                            .id(aProduct.getId())
                                            .build();
                                }
                        ).collect(Collectors.toList())
                )
                .build();
    }

    private CustomerDto domainToDto(Customer customer){

        return CustomerDto.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .dob(customer.getDob())
                .email(customer.getEmail())
                .id(customer.getId())
                .ssn(customer.getSsn())
                .createdDate(dateTimeMapper.asOffsetDateTime(customer.getCreated_at()))
                .lastModifiedDate(dateTimeMapper.asOffsetDateTime(customer.getUpdated_at()))
                .products(
                        customer.getProducts().stream().map(product -> {
                            return ProductDto.builder()
                                    .application_id(product.getApplication_id())
                                    .business_id(product.getBusiness_id())
                                    .cerebro_pid(product.getCerebro_pid())
                                    .id(product.getId())
                                    .createdDate(dateTimeMapper.asOffsetDateTime(product.getCreated_at()))
                                    .lastModifiedDate(dateTimeMapper.asOffsetDateTime(product.getUpdated_at()))
                                    .build();
                        }).collect(Collectors.toList())
                )
                .build();

        //productDtos
    }
}
