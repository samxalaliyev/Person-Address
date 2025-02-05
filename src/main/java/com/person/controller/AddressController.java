package com.person.controller;


import com.person.dto.request.AddressRequestDto;
import com.person.dto.response.AddressResponseDto;
import com.person.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    AddressService addressService;


    @PostMapping
    public ResponseEntity<AddressResponseDto> createAddress(@RequestBody AddressRequestDto requestDto){
        AddressResponseDto responseDto= addressService.createAdrress(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public List<AddressResponseDto> getAllAddresses(){
        return addressService.getAllAddresses();
    }

    @GetMapping("/find-address")
    public ResponseEntity<Object> getAddresById(@RequestParam Long id){
        AddressResponseDto addressResponseDto = addressService.getAddressById(id);
        if (addressResponseDto != null){
           return ResponseEntity.ok(addressResponseDto);
        }else {
            return ResponseEntity.status(404).body("Address not found with this id: " +id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDto> createOrUpdateAddress(@PathVariable Long id,
                                                                    @RequestBody AddressRequestDto requestDto){
        AddressResponseDto responseDto= addressService.createOrUpdateAddress(id,requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
