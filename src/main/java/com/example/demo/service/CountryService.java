package com.example.demo.service;

import com.example.demo.dto.CountryDTO;
import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryService {

    CountryRepository countryRepository;

    CountryService(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    public String addCountry(CountryDTO countryDTO){
        Optional<Country> existingCountryOpt = countryRepository.findByCountryNameAndCityName(countryDTO.getCountryName(),countryDTO.getCityName());
        if(existingCountryOpt.isPresent()) {
            return "Country " + countryDTO.getCountryName() + " already exists with city " + countryDTO.getCityName();
        }
        Country country = new Country();
        country.setCountryName(countryDTO.getCountryName());
        country.setCityName(countryDTO.getCityName());

        countryRepository.save(country);
        return "Country " + countryDTO.getCountryName() + " added with city " + countryDTO.getCityName();
    }
}
