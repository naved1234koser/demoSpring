package com.example.demo.service;

import com.example.demo.dto.CountryDTO;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.City;
import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class CountryService {

    private final CountryRepository countryRepository;
    private final RestTemplate restTemplate;

    CountryService(CountryRepository countryRepository, RestTemplate restTemplate){
        this.countryRepository = countryRepository;
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void init(){
        log.info("Country Service initialized and ready");
    }

    public Country addCountry(String countryName){
        countryRepository.findByCountryName(countryName)
                .ifPresent( c -> {
                    throw new DuplicateResourceException("Country already exists");
                });

        Country country = new Country();
        country.setCountryName(countryName);

        return countryRepository.save(country);
    }

    public City addCity(String countryName,String cityName){
        Country country = countryRepository.findByCountryName(countryName)
                .orElseThrow(() -> new RuntimeException("Country not present"));
        if(country.getCities().stream().anyMatch(c -> cityName.equalsIgnoreCase(c.getName())))
            throw new DuplicateResourceException("City already exists int the given country");
        City city = new City();
        city.setName(cityName);
        country.addCity(city);
        countryRepository.save(country);
        return city;
    }

    public CountryDTO getById(Long id){
        Country country = countryRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Country", id));
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setCountryName(country.getCountryName());
        countryDTO.setCityNames(country.getCities().stream().map(City::getName).toList());
        return countryDTO;
    }

    public List<CountryDTO> getAllCountry(){
        List<Country> countries = countryRepository.findAllFetchJoin();
        return countries
                .stream()
                .map( c -> {
                    CountryDTO dto = new CountryDTO();
                    dto.setCountryName(c.getCountryName());
                    dto.setCityNames(c.getCities().stream().map(City::getName).toList());
                    return dto;
                }).toList();
    }
}
