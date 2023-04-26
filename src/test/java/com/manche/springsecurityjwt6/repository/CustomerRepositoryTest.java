package com.manche.springsecurityjwt6.repository;

import com.manche.springsecurityjwt6.model.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;



    @Test
    public void CustomerRepository_Save_ReturnCustomer(){
        Customer customer = Customer.builder()
                .name("omar")
                .address("Room 24 04253")
                .mail("omar@gmail.com")
                .phoneNumber("+58 2564 96")
                .build();

        Customer customerSave = customerRepository.save(customer);

        Assertions.assertThat(customerSave).isNotNull();
    }

    @Test
    public void CustomerRepository_FindAll_ReturnCustomerList(){
        Customer customer = Customer.builder()
                .name("omar")
                .address("Room 24 04253")
                .mail("omar@gmail.com")
                .phoneNumber("+58 2564 96")
                .build();
        Customer customer2 = Customer.builder()
                .name("carlos")
                .address("Room 25 04253")
                .mail("carlos@gmail.com")
                .phoneNumber("+58 3024 96")
                .build();

        customerRepository.save(customer);
        customerRepository.save(customer2);

       List<Customer> customerList = customerRepository.findAll();

        Assertions.assertThat(customerList).isNotNull();
        Assertions.assertThat(customerList.size()).isEqualTo(2);
    }

    @Test
    @Disabled
    public void CustomerRepository_FindByID_ReturnCustomer(){
        Customer customer = Customer.builder()
                .name("omar")
                .address("Room 24 04253")
                .mail("omar@gmail.com")
                .phoneNumber("+58 2564 96")
                .build();


        customerRepository.save(customer);


        Customer customerByID = customerRepository.findById(1l).get();

        Assertions.assertThat(customerByID).isNotNull();
        Assertions.assertThat(customer.getId()).isEqualTo(1);
    }

    @Test
    public void CustomerRepository_DeleteByID_ReturnNull(){
        Customer customer = Customer.builder()
                .name("omar")
                .address("Room 24 04253")
                .mail("omar@gmail.com")
                .phoneNumber("+58 2564 96")
                .build();


        customerRepository.save(customer);
        customerRepository.deleteById(1l);


        Optional<Customer> customerByID = customerRepository.findById(1l);

        Assertions.assertThat(customerByID).isEmpty();

    }

}