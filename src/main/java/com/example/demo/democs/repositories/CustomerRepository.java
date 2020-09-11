package com.example.demo.democs.repositories;

import com.example.demo.democs.domain.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, UUID> {

        List<Customer> findByDobAndSsn(String dob, String ssn);
        List<Customer> findByDobAndFirstNameAndEmail(String dob, String firstName, String email);

}
