package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue
    private int countryId;
    private String countryName;
    private String cityName;

}
