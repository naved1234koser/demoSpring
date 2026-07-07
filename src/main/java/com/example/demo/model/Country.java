package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // ID should be auto generated and incremental
    private Long countryId;
    private String countryName;
    private String cityName;

}
