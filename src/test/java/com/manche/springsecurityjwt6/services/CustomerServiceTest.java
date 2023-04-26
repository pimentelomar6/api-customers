package com.manche.springsecurityjwt6.services;

import com.manche.springsecurityjwt6.dto.CustomerDTO;
import com.manche.springsecurityjwt6.dto.CustomerResponse;
import com.manche.springsecurityjwt6.model.Customer;
import com.manche.springsecurityjwt6.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void getAll() {
        Page<Customer> list = Mockito.mock(Page.class);

        when(customerRepository.findAll(Mockito.any(Pageable.class))).thenReturn(list);

        CustomerResponse customerResponse = customerService.getAll(1,10);

        Assertions.assertThat(customerResponse).isNotNull();



    }

    @Test
    void getById() {
        Customer customer = Customer.builder()
                .name("omar")
                .address("Room 24 04253")
                .mail("omar@gmail.com")
                .phoneNumber("+58 2564 96")
                .build();


        when(customerRepository.findById(1l)).thenReturn(Optional.ofNullable(customer));

        CustomerDTO customerDTO = customerService.getById(1l);
        Assertions.assertThat(customerDTO).isNotNull();
    }

    @Test
    void deleteCustomer() {
        Customer customer = Customer.builder()
                .name("omar")
                .address("Room 24 04253")
                .mail("omar@gmail.com")
                .phoneNumber("+58 2564 96")
                .build();


        when(customerRepository.findById(1l)).thenReturn(Optional.ofNullable(customer));

        customerService.deleteCustomer(1l);
        CustomerDTO customerDTO = customerService.getById(1l);


        assertAll(() -> customerService.deleteCustomer(1l));


    }

    @Test
    void createCustomer() {
        Customer customer = Customer.builder()
                .name("omar")
                .address("Room 24 04253")
                .mail("omar@gmail.com")
                .phoneNumber("+58 2564 96")
                .build();
        CustomerDTO customerDto = CustomerDTO.builder()
                .name("omar")
                .address("Room 24 04253")
                .mail("omar@gmail.com")
                .phoneNumber("+58 2564 96")
                .build();

        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

        assertAll(() -> customerService.createCustomer(customerDto));

    }

    @Test
    void updateCustomer() {
        Customer customer = Customer.builder()
                .name("omar")
                .address("Room 24 04253")
                .mail("omar@gmail.com")
                .phoneNumber("+58 2564 96")
                .build();
        CustomerDTO customerDto = CustomerDTO.builder()
                .name("omar")
                .address("Room 24 04253")
                .mail("omar@gmail.com")
                .phoneNumber("+58 2564 96")
                .build();

        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        when(customerRepository.findById(1l)).thenReturn(Optional.ofNullable(customer));

        customerService.createCustomer(customerDto);

        customer.setName("Carlos");

        customerService.createCustomer(customerDto);

        CustomerDTO customerSearch = customerService.getById(1l);


        Assertions.assertThat(customerSearch.getName()).isEqualTo("Carlos");

    }

    @Test
    void existById() {
    }
}