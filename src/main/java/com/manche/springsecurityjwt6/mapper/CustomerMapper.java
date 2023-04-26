package com.manche.springsecurityjwt6.mapper;

import com.manche.springsecurityjwt6.dto.CustomerDTO;
import com.manche.springsecurityjwt6.model.Customer;

public class CustomerMapper {

    public static CustomerDTO mapToCustomerDto(Customer customer){
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .mail(customer.getMail())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .build();
    }


    public static Customer mapToCustomer(CustomerDTO customerDTO){
        return Customer.builder()
                .id(customerDTO.getId())
                .name(customerDTO.getName())
                .mail(customerDTO.getMail())
                .phoneNumber(customerDTO.getPhoneNumber())
                .address(customerDTO.getAddress())
                .build();
    }
}
