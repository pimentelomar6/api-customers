package com.manche.springsecurityjwt6.services;

import com.manche.springsecurityjwt6.dto.CustomerDTO;
import com.manche.springsecurityjwt6.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse getAll(int pageNumber, int pageSize);
    CustomerDTO getById(Long id);
    void deleteCustomer(Long id);
    void createCustomer(CustomerDTO customerDTO);
    void updateCustomer(CustomerDTO customerDTO);

    boolean existById(Long id);

}
