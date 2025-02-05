package com.person.service;

import com.person.dto.request.AddressRequestDto;
import com.person.dto.response.AddressResponseDto;
import com.person.dto.response.PersonResponseDto;
import com.person.entity.Address;
import com.person.entity.Person;
import com.person.repository.AddressRepository;
import com.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    public AddressResponseDto createAdrress(AddressRequestDto requestDto){
        Person person = personRepository.findById(requestDto.getPersonId())
                .orElseThrow(()->new RuntimeException("Person not found with id: " + requestDto.getPersonId()));

        Address address = new Address();
        address.setCity(requestDto.getCity());
        address.setAddress(requestDto.getAddress());
        address.setPerson(person);

        Address savedAddress = addressRepository.save(address);

        AddressResponseDto responseDto = new AddressResponseDto();
        responseDto.setId(savedAddress.getId());
        responseDto.setCity(savedAddress.getCity());
        responseDto.setAddress(savedAddress.getAddress());
        responseDto.setPersonId(savedAddress.getPerson().getId());

        return responseDto;
    }


    public List<AddressResponseDto> getAllAddresses(){
        List<Address> adresses = addressRepository.findAll();

        return adresses.stream().map(address -> {
            AddressResponseDto responseDto = new AddressResponseDto();
            responseDto.setAddress(address.getAddress());
            responseDto.setCity(address.getCity());
            responseDto.setId(address.getId());
            responseDto.setPersonId(address.getPerson().getId());

            return responseDto;
        }).collect(Collectors.toList());


    }

    public AddressResponseDto getAddressById(Long id) {
        Optional<Address> addressOptional =addressRepository.findById(id);
        if (addressOptional.isPresent()){
            Address address = addressOptional.get();
            AddressResponseDto addressResponseDto = new AddressResponseDto();
            addressResponseDto.setId(address.getId());
            addressResponseDto.setAddress(address.getAddress());
            addressResponseDto.setCity(address.getCity());
            addressResponseDto.setPersonId(address.getPerson().getId());
            return addressResponseDto;
        }
        return null;
    }

    public AddressResponseDto createOrUpdateAddress(Long id, AddressRequestDto requestDto){
        Person person=personRepository.findById(requestDto.getPersonId())
                .orElseThrow(()->new RuntimeException("Person not found with this id: " + requestDto.getPersonId()));
        Address address=addressRepository.findById(id).orElse(new Address());

        address.setId(id);
        address.setCity(requestDto.getCity());
        address.setAddress(requestDto.getAddress());
        address.setPerson(person);

        Address saveAddress=addressRepository.save(address);

        AddressResponseDto addressResponseDto =new AddressResponseDto();
        addressResponseDto.setId(saveAddress.getId());
        addressResponseDto.setAddress(saveAddress.getAddress());
        addressResponseDto.setCity(saveAddress.getCity());
        addressResponseDto.setPersonId(saveAddress.getPerson().getId());
        return addressResponseDto;
    }

    public void deleteAddressById(Long id) {
        Address address=addressRepository.findById(id)
                .orElseThrow(()->new RuntimeException("This addess not found with this id: " + id));

        // Person ile ilişkiyi qopar
        address.setPerson(null);
        addressRepository.save(address);

        // adresi sil
        addressRepository.delete(address);
    }
}
