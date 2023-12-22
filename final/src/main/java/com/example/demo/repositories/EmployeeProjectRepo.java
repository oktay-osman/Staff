package com.example.demo.repositories;

import com.example.demo.models.EmployeeProject;
import com.example.demo.services.CSVReader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class EmployeeProjectRepo {
    private JdbcTemplate jdbcTemplate;
    private CSVReader reader = new CSVReader();

    private String filePath = "static/employee_projects.csv";

    public EmployeeProjectRepo (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addEmployeeProject(Serializable emp) {
        if(emp instanceof EmployeeProject) {
            String sql = "INSERT INTO employee_projects (employee_id, project_id, from_date, to_date) " +
                    "VALUES (?,?,?,?)";
            jdbcTemplate.update(sql, ((EmployeeProject) emp).getEmployeeId(), ((EmployeeProject) emp).getProjectId(),
                    ((EmployeeProject) emp).getFromDate(), ((EmployeeProject) emp).getToDate());
        }
    }

    public List<? extends Serializable> getAllEmployeeProjects() {
        //TODO fix path and create run configuration
        return reader.read("C:\\Users\\my\\IdeaProjects\\Staff\\Staff\\final\\src\\main\\resources\\static\\employee_projects.csv");
    }
}
