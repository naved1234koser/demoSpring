package com.example.demo.service;

import com.example.demo.dto.CountryDTO;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    CountryService(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    public ResponseEntity<String> addCountry(CountryDTO countryDTO){
        countryRepository.findByCountryNameAndCityName(countryDTO.getCountryName(),countryDTO.getCityName())
                .ifPresent( c -> {
                    throw new DuplicateResourceException("Country/City combination already exists");
                });

        Country country = new Country();
        country.setCountryName(countryDTO.getCountryName());
        country.setCityName(countryDTO.getCityName());

        countryRepository.save(country);
        String res = "Country " + countryDTO.getCountryName() + " added with city " + countryDTO.getCityName();
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    public ResponseEntity<CountryDTO> getById(Long id){
        Country country = countryRepository.findByCountryId(id)
                .orElseThrow( () -> new ResourceNotFoundException("Country", id));
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setCountryName(country.getCountryName());
        countryDTO.setCityName(country.getCityName());
        return ResponseEntity.status(HttpStatus.FOUND).body(countryDTO);
    }
}
