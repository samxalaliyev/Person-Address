package com.person.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String address;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

}
