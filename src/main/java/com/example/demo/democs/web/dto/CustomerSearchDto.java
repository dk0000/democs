package com.example.demo.democs.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerSearchDto {

    private String ssn;
    private String dob;
    private String firstName;
    private String lastName;
    private String email;

}
