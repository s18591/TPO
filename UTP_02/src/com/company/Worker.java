package com.company;

import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.Temporal;
import java.util.Date;

public class Worker extends Employee {

    // attributes
    // * employment date
    // * bonus

    // (assignment 03)
    // attributes:
    // * has bonus
    //
    // methods:
    // * seniority is longer than given number of years (seniority - staї)
    // * seniority is longer than given number of months
    // * has bonus greater than given amount of money

    LocalDate employmentDate;
    BigDecimal bonus;
    boolean hasBonus = true;

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }


    public Period getEmploymentLength() {
        Period period = Period.between(employmentDate, LocalDate.now());
        return period;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public Worker(String firstName) {
        super(firstName);
    }

    public Worker(String firstName, String surName, Date date, BigDecimal salary, Manager manager, LocalDate employmentDate, BigDecimal bonus) {
        super(firstName, surName, date, salary, manager);
        this.employmentDate = employmentDate;
        this.bonus = bonus;
    }

    public Worker(String firstName, String surName, Date date, BigDecimal salary, LocalDate employmentDate, BigDecimal bonus) {
        super(firstName, surName, date, salary);
        this.employmentDate = employmentDate;
        this.bonus = bonus;
    }
    public boolean isLongerYear(Period period, int year){
        return period.getYears() > year;
    }
    public  boolean isLongerMonth(Period period, int month){
        return period.getYears() * 12 + period.getMonths() > month;
    }
    public boolean bonusGreaterThenMoney(Worker worker , int money){
        if(worker.getBonus().intValue() > money){
            return true;
        }else
            return false;
    }



}