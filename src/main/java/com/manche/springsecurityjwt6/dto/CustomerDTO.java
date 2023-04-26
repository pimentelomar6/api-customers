package com.manche.springsecurityjwt6.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {
    private Long id;
    private String name;
    private String mail;
    private String phoneNumber;
    private String address;

}
