package com.person.dto.response;

import lombok.Data;

@Data
public class AddressResponseDto {
    private Long id;
    private String city;
    private String address;
    private Long personId;
}
