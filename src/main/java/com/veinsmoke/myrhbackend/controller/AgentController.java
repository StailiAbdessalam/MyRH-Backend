package com.veinsmoke.myrhbackend.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agent")
public class AgentController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Agent";
    }
}
