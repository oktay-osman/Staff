package com.example.demo.services;

import com.example.demo.models.EmployeeProject;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements Reader{
    @Override
    public List<? extends Serializable> read (String pathFile) {
        List<EmployeeProject> employeeProjects = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(pathFile))) {

            if(br.readLine().toLowerCase().contains("id")) {
                br.readLine();
            }

            String line;
            while((line = br.readLine()) != null) {
                String[] values = line.split(", *");
                if(values.length != 0) {
                    if(Integer.parseInt(values[0]) <= 0 || Integer.parseInt(values[1]) <= 0 ) {
                        br.readLine();
                    } else {
                        employeeProjects.add(new EmployeeProject(Long.parseLong(values[0]),
                                Long.parseLong(values[1]), LocalDate.parse(values[2]),
                                values[3].isEmpty() || values[3].equals("NULL") ? null : LocalDate.parse(values[3])));
                    }
                } else {
                    br.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return employeeProjects;
    }
}
