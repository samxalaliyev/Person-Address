package com.person.controller;

import com.person.dto.request.PersonRequestDto;
import com.person.dto.response.PersonResponseDto;
import com.person.dto.response.PersonWithAddressesResponseDto;
import com.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<PersonResponseDto> createPerson(@RequestBody PersonRequestDto requestDto){
        PersonResponseDto response = personService.createPerson(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<PersonResponseDto> getAllPersons(){
       return personService.getAllPerson();
    }

    @GetMapping("/find-person")
    public ResponseEntity<Object> getPersonById(@RequestParam Long id){
        PersonResponseDto responseDto =personService.getPersonById(id);
        if (responseDto != null) {
            return ResponseEntity.ok(responseDto); // 200 OK
        } else {
            return ResponseEntity.status(404).body("Person not found with ID: " + id); // 404 Not Found
        }
    }

    @GetMapping("/person-with-addresses/{id}")
    public ResponseEntity<?> getPersonWithAddressesById(@PathVariable Long id) {
    try {
        PersonWithAddressesResponseDto responseDto = personService.getPersonWithAddressById(id);
        return ResponseEntity.ok(responseDto); //200 ok
    }catch (RuntimeException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    }






















}
