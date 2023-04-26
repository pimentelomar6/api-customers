package com.manche.springsecurityjwt6.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manche.springsecurityjwt6.dto.CustomerDTO;
import com.manche.springsecurityjwt6.dto.CustomerResponse;
import com.manche.springsecurityjwt6.services.CustomerService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@Disabled
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;


    private CustomerDTO customerDTO;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init(){
        customerDTO = CustomerDTO.builder()
                .name("Carlos Andrade")
                .mail("carlos_42@gmail.com")
                .phoneNumber("+5824821693")
                .address("Room 24, Look 42")
                .build();
    }

    @Test
    @Disabled
    void getAll() throws Exception{
        CustomerResponse customerResponse = CustomerResponse.builder().pageSize(10).last(true).pageNumber(1).content(Arrays.asList(customerDTO)).build();
        when(customerService.getAll(1,10)).thenReturn(customerResponse);

        ResultActions resultActions = mockMvc.perform(get("/api/v1/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .param("pageNumber", "1")
                .param("pageSize","10"));


        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()", CoreMatchers.is(customerResponse.getContent().size())));
    }


}