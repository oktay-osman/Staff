package com.example.demo.models;

import java.time.LocalDate;

public class EmployeeProjects {
    private long employeeId;
    private long projectId;
    private LocalDate fromDate;
    private LocalDate toDate;

    public EmployeeProjects (long employeeId, long projectId, LocalDate fromDate, LocalDate toDate) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public long getEmployeeId () {
        return employeeId;
    }

    public void setEmployeeId (long employeeId) {
        this.employeeId = employeeId;
    }

    public long getProjectId () {
        return projectId;
    }

    public void setProjectId (long projectId) {
        this.projectId = projectId;
    }

    public LocalDate getFromDate () {
        return fromDate;
    }

    public void setFromDate (LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate () {
        return toDate;
    }

    public void setToDate (LocalDate toDate) {
        this.toDate = toDate;
    }
}
