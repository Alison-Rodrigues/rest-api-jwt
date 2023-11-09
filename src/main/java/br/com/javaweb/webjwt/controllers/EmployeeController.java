package br.com.javaweb.webjwt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class EmployeeController {

    @GetMapping("/employees")
    public String listAll() {
        return "Funfando";
    }
}
