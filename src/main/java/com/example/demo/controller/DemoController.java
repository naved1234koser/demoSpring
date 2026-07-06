package com.example.demo.controller;

import com.example.demo.dto.CountryDTO;
import com.example.demo.service.CountryService;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {

    CountryService countryService;

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
    public String addCountry(@RequestBody CountryDTO countryDTO){
       return countryService.addCountry(countryDTO);
    }

}
