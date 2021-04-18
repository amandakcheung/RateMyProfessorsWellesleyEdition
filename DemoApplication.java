package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hellox %s!", name);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/cgi-bin/dump.cgi")
    public RateProfessor FormSubmission(@RequestBody Professor body) {
        System.out.println("hello");
        RateProfessor prof = new RateProfessor(body.getName(), body.getExperience(), body.getDiff(), body.getTake(), body.getTutors(), body.getTextbooks(), body.getAttendance(), body.getHours(), body.getStructure(), body.getStudying());
        System.out.println(prof.toString());
        return prof;
    }
}
