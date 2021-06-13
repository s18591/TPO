package com.company;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public abstract class Employee extends Person {
    //
    // attributes:
    // * salary (use BigDecimal type for representing currency values)
    // * manager (empty if at top of hierarchy)


    /////////////////////////////
    // (assignment 03)
    // methods:
    // * salary is greater than given amount of money
    // * salary is less than given amount of money
    // * compare salary with other employee salary

    BigDecimal salary;
    Manager manager;

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }


    protected Employee(String firstName) {
        super(firstName);
    }

    public Employee(String firsName, String surname, Date date, BigDecimal salary, Manager manager) {
        super(firsName, surname, date);
        this.salary = salary;
        this.manager = manager;
    }

    public Employee(String firstName, String surname, Date date, BigDecimal salary) {
        super(firstName, surname, date);
        this.salary = salary;
    }

    public boolean isGreaterSlary(Employee employee) {
        if (employee.getSalary().intValue() > getSalary().intValue()) {
            return true;
        } else return false;
    }

    public boolean isLessSalary(Employee employee) {
        if (employee.getSalary().intValue() < getSalary().intValue()) {
            return true;
        }
        return false;
    }

    public boolean greaterSalary(Employee employe, BigDecimal money) {
        if (employe.getSalary().intValue() > money.intValue()) {
            return true;
        }
        return false;
    }

    public boolean isSalaryLess(Employee employee, BigDecimal money) {
        if (employee.getSalary().intValue() < money.intValue()) {
            return true;
        }
        return false;
    }

    public int compareSalary(Employee employee) {
        if (isGreaterSlary(employee)) {
            return 1;
        }
        if (isLessSalary(employee)) {
            return -1;
        } else
            return 0;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}