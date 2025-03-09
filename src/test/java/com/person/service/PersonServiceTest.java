package com.person.service;

import com.person.dto.request.PersonRequestDto;
import com.person.dto.response.PersonResponseDto;
import com.person.entity.Person;
import com.person.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class )
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository repository;

    @Test
    void whenCreatePersonThenSuccess(){
        PersonRequestDto requestDto= new PersonRequestDto();
        requestDto.setName("Tomas");
        requestDto.setAge(5);

        Person savedPerson= new Person();
        savedPerson.setId(1L);
        savedPerson.setName("Tomas");
        savedPerson.setAge(5);

        when(repository.save(any(Person.class))).thenReturn(savedPerson);

        PersonResponseDto responseDto= personService.createPerson(requestDto);

        verify(repository,times(1)).save(any(Person.class));

        assertNotNull(responseDto);
        assertEquals(1L,responseDto.getId());
        assertEquals("Tomas",responseDto.getName());
        assertEquals(5,responseDto.getAge());


    }

}