package com.example.demo.services;

import com.example.demo.models.EmployeeProject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class CSVWriter implements Writer{
    @Override
    public void write (List<? extends Serializable> items, String filePath) {
        try(FileWriter writer = new FileWriter(filePath)) {
            writer.write("EmployeeID, ProjectID, DateFrom, DateTo\n");
            for (Serializable obj: items) {
                if(obj instanceof EmployeeProject) {
                    writer.append(((EmployeeProject) obj).toStringCSVFormat()).append("\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
