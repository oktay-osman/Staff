package com.example.demo.services;

import com.example.demo.models.EmployeeProject;
import com.example.demo.repositories.EmployeeProjectRepo;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class EmployeeProjectService {
    private final EmployeeProjectRepo employeeProjectRepo;

    public EmployeeProjectService (EmployeeProjectRepo employeeProjectRepo) {
        this.employeeProjectRepo = employeeProjectRepo;
    }

    public void addEmployeeProject(EmployeeProject emp) {
        employeeProjectRepo.addEmployeeProject(emp);
    }

    public List<? extends Serializable> getAllEmployeeProjects() {
        return employeeProjectRepo.getAllEmployeeProjects();
    }

    public void addRecordsToDB () {
        employeeProjectRepo.addRecordsToDB();
    }
}
