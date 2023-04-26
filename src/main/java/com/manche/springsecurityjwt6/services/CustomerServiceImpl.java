package com.manche.springsecurityjwt6.services;

import com.manche.springsecurityjwt6.dto.CustomerDTO;
import com.manche.springsecurityjwt6.dto.CustomerResponse;
import com.manche.springsecurityjwt6.model.Customer;
import com.manche.springsecurityjwt6.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.manche.springsecurityjwt6.mapper.CustomerMapper.mapToCustomer;
import static com.manche.springsecurityjwt6.mapper.CustomerMapper.mapToCustomerDto;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse getAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Customer> list = customerRepository.findAll(pageable);
        List<CustomerDTO> customers = list.getContent()
                .stream()
                .map(c -> mapToCustomerDto(c))
                .collect(Collectors.toList());

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setContent(customers);
        customerResponse.setPageNumber(list.getNumber());
        customerResponse.setPageSize(list.getSize());
        customerResponse.setTotalElements((int) list.getTotalElements());
        customerResponse.setTotalPages(list.getTotalPages());
        customerResponse.setLast(list.isLast());

        return customerResponse;
    }

    @Override
    public CustomerDTO getById(Long id) {
        Customer customer = customerRepository.findById(id).get();
        return mapToCustomerDto(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);

    }

    @Override
    public void createCustomer(CustomerDTO customerDTO) {
        customerRepository.save(mapToCustomer(customerDTO));

    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        customerRepository.save(mapToCustomer(customerDTO));

    }

    @Override
    public boolean existById(Long id) {
        return customerRepository.existsById(id);
    }
}
