package com.manche.springsecurityjwt6.controller;

import com.manche.springsecurityjwt6.dto.CustomerDTO;
import com.manche.springsecurityjwt6.dto.CustomerResponse;
import com.manche.springsecurityjwt6.exceptions.CustomerNotFoundException;
import com.manche.springsecurityjwt6.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<CustomerResponse> getAll(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        if(customerService.getAll(pageNumber, pageSize).getContent().isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customerService.getAll(pageNumber, pageSize));
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getById(@PathVariable("id") Long id){
        if(!(customerService.existById(id))){
            throw new CustomerNotFoundException("El id no existe");
        }
        return ResponseEntity.ok(customerService.getById(id));
    }

    @PostMapping
    public ResponseEntity addCustomer(@RequestBody CustomerDTO customerDTO){
        if(!(customerDTO.getId() == null)){
            return ResponseEntity.badRequest().build();
        }
        customerService.createCustomer(customerDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateCustomer(@RequestBody CustomerDTO customerDTO){
        if(customerDTO.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        customerService.updateCustomer(customerDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCustomer(@PathVariable("id") Long id){
        if(!(customerService.existById(id))){
            return ResponseEntity.badRequest().build();
        }
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }




}
