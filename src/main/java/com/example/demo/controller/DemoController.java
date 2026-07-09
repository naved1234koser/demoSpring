package com.example.demo.controller;

import com.example.demo.dto.CountryDTO;
import com.example.demo.model.Country;
import com.example.demo.service.CountryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class DemoController {

    private final CountryService countryService;

    DemoController(CountryService countryService){
        this.countryService = countryService;
    }

    @GetMapping(value = "/")
    public String helloWorld(){
        return "Greetings!";
    }

    @GetMapping(value = "/get")
    public String getApi(){
        return "Hello from DemoApp";
    }

    @PostMapping(value = "/post")
    public void post(@RequestParam String param){
        System.out.println("Param posted : " + param);
    }

    @PostMapping(value = "/countries")
    public ResponseEntity<Country> addCountry(@Valid @RequestBody CountryDTO countryDTO){
       return ResponseEntity.status(HttpStatus.CREATED).body(countryService.addCountry(countryDTO));
    }

    @GetMapping(value = "/countries/{id}")
    public ResponseEntity<CountryDTO> getById(@PathVariable @Positive(message = "ID must be a positive number") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(countryService.getById(id));
    }

    @GetMapping(value = "/countries")
    public ResponseEntity<List<CountryDTO>> getAllCountry(){
        return ResponseEntity.ok(countryService.getAllCountry());
    }
}
