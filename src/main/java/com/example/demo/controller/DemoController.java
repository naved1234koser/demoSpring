package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

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

}
