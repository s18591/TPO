package com.company;

import java.math.BigDecimal;



public final class PayrollEntry {

    private final Employee _employee;
    private final BigDecimal _salaryPlusBonus;

    public Employee get_employee() {
        return _employee;
    }



    public PayrollEntry(Employee employee, BigDecimal salary, BigDecimal bonus) {
        if(salary == null || bonus == null){
         salary = new BigDecimal(5000);
         bonus = new BigDecimal(500);
        }
        _employee = employee;
        _salaryPlusBonus = salary.add(bonus); // validate whether salary and bonus are not null
    }
    public BigDecimal get_salaryPlusBonus() {
        return _salaryPlusBonus;
    }

    @Override
    public String toString() {
        return  _employee +
                ", _salaryPlusBonus=" + _salaryPlusBonus +
                '\n';
    }
}