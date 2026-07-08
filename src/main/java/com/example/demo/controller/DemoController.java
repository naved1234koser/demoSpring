package com.example.demo.controller;

import com.example.demo.dto.CountryDTO;
import com.example.demo.model.Country;
import com.example.demo.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @PostMapping(value = "/addCountry")
    public ResponseEntity<Country> addCountry(@RequestBody CountryDTO countryDTO){
       return ResponseEntity.status(HttpStatus.CREATED).body(countryService.addCountry(countryDTO));
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<CountryDTO> getById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(countryService.getById(id));
    }

    @GetMapping(value = "getallcountry")
    public ResponseEntity<List<CountryDTO>> getAllCountry(){
        return ResponseEntity.ok(countryService.getAllCountry());
    }
}
