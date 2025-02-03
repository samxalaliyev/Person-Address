package com.person.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class PersonWithAddressesResponseDto {
    private Long id;
    private String name;
    private Integer age;
    private List<AddressResponseDto> addresses; // Kişiye ait adresler
}