package com.example.demo.config;

import com.example.demo.model.City;
import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
@Slf4j
public class DevDataInitializer {

    private final CountryRepository countryRepository;

    public DevDataInitializer(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init(){
        if(countryRepository.count() == 0) {
            Country country = new Country();
            country.setCountryName("India");
            City city = new City();
            city.setName("Mumbai");
            country.addCity(city);
            countryRepository.save(country);
            log.info("Dev data seeded");
        }
    }

}
