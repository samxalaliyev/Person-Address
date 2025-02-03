package com.person.service;


import com.person.dto.request.PersonRequestDto;
import com.person.dto.response.AddressResponseDto;
import com.person.dto.response.PersonResponseDto;
import com.person.dto.response.PersonWithAddressesResponseDto;
import com.person.entity.Person;
import com.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonResponseDto createPerson(PersonRequestDto requestDto){
        Person person = new Person();
        person.setName(requestDto.getName());
        person.setAge(requestDto.getAge());

        Person savePerson = personRepository.save(person);

        PersonResponseDto responseDto = new PersonResponseDto();
        responseDto.setId(savePerson.getId());
        responseDto.setName(savePerson.getName());
        responseDto.setAge(savePerson.getAge());

        return responseDto;
    }

    public List<PersonResponseDto> getAllPerson(){
        List<Person> persons = personRepository.findAll();

        return persons.stream().map(person -> {
            PersonResponseDto responseDto = new PersonResponseDto();
            responseDto.setId(person.getId());
            responseDto.setName(person.getName());
            responseDto.setAge(person.getAge());
            return responseDto;
        }).collect(Collectors.toList());
    }

    public PersonResponseDto getPersonById(Long id) {
        Optional<Person> personOptional =personRepository.findById(id);
        if (personOptional.isPresent()){
            Person person = personOptional.get();
            PersonResponseDto responseDto = new PersonResponseDto();
            responseDto.setId(person.getId());
            responseDto.setAge(person.getAge());
            responseDto.setName(person.getName());
            return responseDto;
        }
        return null;
    }

    public PersonWithAddressesResponseDto getPersonWithAddressById(Long id) {
        Optional<Person> personOptional = personRepository.findById(id);

        if (personOptional.isPresent()){
            Person person=personOptional.get();

            List<AddressResponseDto> addresses = person.getAddresses().stream()
                    .map(address -> {
                        AddressResponseDto dto=new AddressResponseDto();
                        dto.setId(address.getId());
                        dto.setCity(address.getCity());
                        dto.setAddress(address.getAddress());
                        return dto;
                    }).collect(Collectors.toList());

            PersonWithAddressesResponseDto responseDto=new PersonWithAddressesResponseDto();
            responseDto.setId(person.getId());
            responseDto.setName(person.getName());
            responseDto.setAge(person.getAge());
            responseDto.setAddresses(addresses);

            return responseDto;
        }else {
            throw new RuntimeException("Person not found with this id: " +id);
        }
    }
}
