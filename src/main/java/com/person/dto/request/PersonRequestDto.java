package com.person.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonRequestDto {
    private String name;
    private Integer age;
}
