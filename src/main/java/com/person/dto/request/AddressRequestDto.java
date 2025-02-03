package com.person.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressRequestDto {
    private String city;
    private String address;
    private Long personId;

}
