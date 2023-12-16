package com.example.demo.controllers;

import com.example.demo.services.EmployeeProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/emp-projects")
public class EmployeeProjectController {
    private EmployeeProjectService employeeProjectService;

    public EmployeeProjectController (EmployeeProjectService employeeProjectService) {
        this.employeeProjectService = employeeProjectService;
    }
    @GetMapping("/all")
    public List<? extends Serializable> getEmpProjects() {
        return employeeProjectService.getAllEmployeeProjects();
    }
}
