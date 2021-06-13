package com.company;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Trainee extends Employee {

    // attributes:
    // * practice start date
    // * practice length (in days)

    // (assignment 03)
    // * practice length is shorter than given number of days
    // * practice length is longer than given number of days

    LocalDate practiceStartDate;
    long practiceLength = 150;

    public LocalDate getPracticeStartDate() {
        return practiceStartDate;
    }

    public void setPracticeStartDate(LocalDate practiceStartDate) {
        this.practiceStartDate = practiceStartDate;
    }

    public Period getPracticeLength() {
        Period period = Period.between(practiceStartDate, LocalDate.now());
        return period;
    }

    public boolean isLongerPractice(Period period, int days) {
        return (period.getYears() * 364 + period.getMonths() * 30 + period.getDays()) > days;
    }

    public boolean isShorterPractice(Period period, int days) {
        return (period.getYears() * 364 + period.getMonths() * 12 + period.getDays()) < days;
    }

    public Trainee(String firstName) {
        super(firstName);
    }

    public Trainee(String firsName, String surname, Date date, BigDecimal salary, Manager manager, LocalDate practiceStartDate) {
        super(firsName, surname, date, salary, manager);
        this.manager = manager;
        this.practiceStartDate = practiceStartDate;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}