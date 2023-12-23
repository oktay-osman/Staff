package com.example.demo.controllers;

import com.example.demo.models.EmployeeProject;
import com.example.demo.services.EmployeeProjectService;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/emp-projects")
public class EmployeeProjectController {
    private final EmployeeProjectService employeeProjectService;

    public EmployeeProjectController (EmployeeProjectService employeeProjectService) {
        this.employeeProjectService = employeeProjectService;
    }
    @GetMapping("/all")
    public List<? extends Serializable> getEmpProjects() {
        return employeeProjectService.getAllEmployeeProjects();
    }

    @PostMapping("/add-emp-project")
    public String addEmpProject (@RequestBody Serializable empProject) {
        try {
            if(empProject instanceof EmployeeProject) {
                employeeProjectService.addEmployeeProject((EmployeeProject) empProject);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Successfully added Employee-project info.";
    }

    @PostConstruct
    public void addRecordsToDB() {
        try {
            employeeProjectService.addRecordsToDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
