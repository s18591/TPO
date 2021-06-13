package com.company;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class Manager extends Worker {

    // attributes
    // * subordinates (a list of immediate subordinates)
    // * all subordinates (a list of subordinates in all hierarchy)

    List<Worker> workers = new ArrayList<>();
    List<Trainee> trainees = new ArrayList<>();
    List<Employee> employees = new ArrayList<>();
    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public void setTrainees(List<Trainee> trainees) {
        this.trainees = trainees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Manager(String firstName) {
        super(firstName);
    }
    public Manager(String firstName, String surName, Date date, BigDecimal salary, LocalDate employmentDate, BigDecimal bonus,List<Employee> employees){
        super(firstName,surName,date,salary,employmentDate,bonus);
        this.employees = employees;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}