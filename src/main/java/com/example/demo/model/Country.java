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
    @GeneratedValue // I will patch it when using PostGre
    private Long countryId;
    private String countryName;
    private String cityName;

}
