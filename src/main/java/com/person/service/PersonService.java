package com.person.service;


import com.person.dto.request.PersonRequestDto;
import com.person.dto.response.AddressResponseDto;
import com.person.dto.response.PersonResponseDto;
import com.person.dto.response.PersonWithAddressesResponseDto;
import com.person.entity.Person;
import com.person.exception.PersonException;
import com.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "person")
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

    @Cacheable(value = "person", key = "#id")
    public PersonResponseDto getPersonById(Long id) {

        //without exception version

//        Optional<Person> personOptional =personRepository.findById(id);
//        if (personOptional.isPresent()){
//            Person person = personOptional.get();
//            PersonResponseDto responseDto = new PersonResponseDto();
//            responseDto.setId(person.getId());
//            responseDto.setAge(person.getAge());
//            responseDto.setName(person.getName());
//            return responseDto;
//        }
//        return null;

        // with exception version

        Person person = personRepository.findById(id)
                    .orElseThrow(() -> new PersonException("Person not found with id: " + id));

        PersonResponseDto responseDto = new PersonResponseDto();
        responseDto.setId(person.getId());
        responseDto.setAge(person.getAge());
        responseDto.setName(person.getName());
        return responseDto;

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
                        dto.setPersonId(person.getId());
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

    public void deletePersonById(Long id) {
        Person person=personRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Person not found with id: " +id));

        personRepository.delete(person);
    }

    public PersonResponseDto updateOrCreatePerson(Long id, PersonRequestDto requestDto){
        Person person = personRepository.findById(id).orElse(new Person());

        person.setName(requestDto.getName());
        person.setAge(requestDto.getAge());

        Person updatePerson=personRepository.save(person);

        PersonResponseDto responseDto=new PersonResponseDto();
        responseDto.setId(updatePerson.getId());
        responseDto.setName(updatePerson.getName());
        responseDto.setAge(updatePerson.getAge());
        return responseDto;
    }
    public PersonResponseDto updatePerson(Long id, PersonRequestDto requestDto){
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found with id: " + id));
        person.setName(requestDto.getName());
        person.setAge(requestDto.getAge());

        Person updatePerson=personRepository.save(person);

        PersonResponseDto responseDto=new PersonResponseDto();
        responseDto.setId(updatePerson.getId());
        responseDto.setName(updatePerson.getName());
        responseDto.setAge(updatePerson.getAge());
        return responseDto;
    }

}
