package com.company.Tests;

import com.company.*;
import org.junit.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
   @org.junit.Test
    public void test(){
       List<Employee> employees = new ArrayList<>();
       List<Employee> employees1 = new ArrayList<>();
       Manager manager = new Manager("Denis","Slusar",new Date(1985,12,11),new BigDecimal(30000),LocalDate.of(2000,12,13),new BigDecimal(1000),employees);
       Manager manager1 = new Manager("Kibodzudzi","Mudzan",new Date(900,12,31),new BigDecimal(29999),LocalDate.of(1986,8,8),new BigDecimal(1001),employees1);
      /*1*/ employees.add(new Worker("Yrii","Anicewich",new Date(1996,8,6),new BigDecimal(10000),manager,LocalDate.of(2016,4,3),new BigDecimal(500)));
      /*2*/ employees.add(new Worker("Nerevarine","Morrowindowich",new Date(1972,5,5),new BigDecimal(5000),manager,LocalDate.of(2000,6,9),new BigDecimal(600)));
      /*3*/ employees.add(new Worker("Tandziro","Kamado",new Date(1985,10,11),new BigDecimal(6000),manager,LocalDate.of(2002,4,4),new BigDecimal(400)));
      /*4*/ employees.add(manager);
      /*5*/ employees.add(new Worker("Akatsuki","Amaterasu",new Date(2000,11,11),new BigDecimal(5000),LocalDate.of(2017,11,11),new BigDecimal(600)));
      /*6*/ employees1.add(new Worker("Nezuko","Kamado",new Date(1978,10,10),new BigDecimal(7000),manager1,LocalDate.of(2000,4,4),new BigDecimal(200)));
      /*7*/ employees.add(new Worker("Mary","Oldmerch",new Date(1978,10,10),new BigDecimal(7500),manager,LocalDate.of(1995,5,6),new BigDecimal(100)));
      /*8*/ employees1.add(new Worker("Pavlo","Zinewich",new Date(1974,4,5),new BigDecimal(9000),manager1,LocalDate.of(1998,9,5),new BigDecimal(700)));
      /*10*/ employees1.add(new Worker("Vladyslawa","Rogowenko",new Date(2001,6,26),new BigDecimal(7550),manager1,LocalDate.of(2019,1,31),new BigDecimal(430)));
      /*11*/ employees.add(new Worker("Jaroslaw","Wowkotrub",new Date(2000,8,17),new BigDecimal(9500),manager,LocalDate.of(2016,10,22),new BigDecimal(800)));
      /*12*/ employees1.add(new Worker("Irena","Kurganova",new Date(2001,6,13),new BigDecimal(3000),manager1,LocalDate.of(2019,8,8),new BigDecimal(500)));
      /*13*/ employees.add(new Worker("Oleg","Luganskowich",new Date(1986,7,7),new BigDecimal(4666),manager,LocalDate.of(2005,9,22),new BigDecimal(700)));

      /*19*/ employees.add(new Trainee("Sergii","Zastrowski",new Date(1966,12,24),new BigDecimal(555),manager,LocalDate.of(1995,7,5)));
      /*18*/ employees.add(new Trainee("Swietlana","Zastrowskaja",new Date(1965,12,25),new BigDecimal(200),manager,LocalDate.of(1995,7,4)));
      /*16*/ employees.add(new Trainee("Geralt","Fromrivia",new Date(1994,6,27),new BigDecimal(900),manager,LocalDate.of(2002,8,30)));

      /*14*/ employees1.add(new Trainee("Benedikt","Kamemberch",new Date(1999,7,31),new BigDecimal(888),manager1,LocalDate.of(2001,1,1)));
      /*15*/ employees1.add(new Trainee("Hideo","Kojima",new Date(1985,1,1),new BigDecimal(700),manager1,LocalDate.of(2008,1,18)));
      /*17*/ employees1.add(new Trainee("Volodymyr","Dowhii",new Date(1984,4,14),new BigDecimal(400),manager1,LocalDate.of(2003,6,15)));
      /*20*/ employees1.add(new Trainee("Toho","Mitsuhiko",new Date(1945,9,1),new BigDecimal(666),manager1,LocalDate.of(1986,5,9)));

       HumanResourcesStatistics humanResourcesStatistics = new HumanResourcesStatistics();
       //System.out.println(humanResourcesStatistics.longestSeniority(employees));
       System.out.println("List of emp : " + HumanResourcesStatistics.payroll(employees));
       //System.out.println("List of emp1 : " + HumanResourcesStatistics.payroll(employees1));
       System.out.println("====================================================");
       System.out.println("SubordinateList : " + HumanResourcesStatistics.subordinatesPayroll(manager1));
       System.out.println("====================================================");
       System.out.println("HighestSalaryWithoutBunus : " + humanResourcesStatistics.highestSalaryWithoutBonus(employees));
       System.out.println("====================================================");
       System.out.println("HighestSalaryWithBonus : " + humanResourcesStatistics.highestSalaryWithBonus(employees));
       System.out.println("Total Bonus : " + HumanResourcesStatistics.bonusTotal(employees));
       System.out.println("====================================================");
       System.out.println("SurnameA : " + humanResourcesStatistics.surnameA(manager));
       System.out.println("====================================================");
       System.out.println("SalaryBiggerThen1000 : " + humanResourcesStatistics.salaryBiggerThen1000(manager));
       System.out.println("LongestPeriod : " + humanResourcesStatistics.longestSeniority(employees));
       Assert.assertEquals(employees.get(0).getSurname(),employees.get(0).getSurname());
       Assert.assertNotEquals(employees.get(8).getSalary(),employees.get(1).getSalary());
       Assert.assertNotEquals(employees.get(5).getSalary(),employees.get(4).getSalary());
       Assert.assertEquals(humanResourcesStatistics.highestSalaryWithBonus(employees),humanResourcesStatistics.highestSalaryWithBonus(employees));
       Assert.assertEquals(humanResourcesStatistics.highestSalaryWithoutBonus(employees),humanResourcesStatistics.highestSalaryWithoutBonus(employees));
      System.out.println(HumanResourcesStatistics.olderThenAndEarnMore(employees,employees.get(0),(o, o2) -> o2.isOlder(o),(o, o2) -> o2.isLessSalary(o)));
     // System.out.println(HumanResourcesStatistics.practiceLengthLongerThan(employees,25,employee -> ((Trainee)employee).isLongerPractice(((Trainee)employee).getPracticeLength(),25),employee -> employee.setSalary(employee.getSalary().multiply(new BigDecimal(0.05)))));
     // System.out.println(HumanResourcesStatistics.seniorityLongerThan(employees,8,employee -> (((Worker)employee).isLongerMonth(((Worker)employee).getEmploymentLength(),8)),employee -> ((Worker)employee).setBonus((((Worker)employee)).getBonus().add(new BigDecimal(300)))));
      //System.out.println(HumanResourcesStatistics.seniorityBetweenOneAndThreeYears(employees,employee -> (((Worker)employee).isLongerYear(((Worker)employee).getEmploymentLength(),1)),employee -> ((Worker)employee).isLongerYear(((Worker)employee).getEmploymentLength(),3),employee -> employee.getSalary().multiply(new BigDecimal(0.1))));
    //System.out.println(HumanResourcesStatistics.seniorityLongerThan(employees,employees.get(0),(employee, employee2) -> ((Worker)employee2).isLongerMonth(((Worker) employee2).getEmploymentLength(),((Worker)employee).getEmploymentLength().getMonths()),(employee, employee2) -> employee.getSalary().intValue()>employee2.getSalary().intValue(),(employee, employee2) -> employee.setSalary(employee2.getSalary())));
     // System.out.println(HumanResourcesStatistics.seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(employees,10,employee -> ((((Worker)employee).isLongerYear(((Worker)employee).getEmploymentLength(),2))),employee -> (((Worker)employee).isLongerYear(((Worker)employee).getEmploymentLength(),4)),employee -> (employee.getAge()>10)));
      Assert.assertEquals((HumanResourcesStatistics.olderThenAndEarnMore(employees,employees.get(0),(o, o2) -> o2.isOlder(o),(o, o2) -> o2.isLessSalary(o))),HumanResourcesStatistics.olderThenAndEarnMore(employees,employees.get(0),(o, o2) -> o2.isOlder(o),(o, o2) -> o2.isLessSalary(o)));
      Assert.assertEquals(HumanResourcesStatistics.practiceLengthLongerThan(employees,25,employee -> ((Trainee)employee).isLongerPractice(((Trainee)employee).getPracticeLength(),25),employee -> employee.setSalary(employee.getSalary().multiply(new BigDecimal(0.05)))),HumanResourcesStatistics.practiceLengthLongerThan(employees,25,employee -> ((Trainee)employee).isLongerPractice(((Trainee)employee).getPracticeLength(),25),employee -> employee.setSalary(employee.getSalary().multiply(new BigDecimal(0.05)))));
      Assert.assertEquals(HumanResourcesStatistics.seniorityBetweenOneAndThreeYears(employees,employee -> (((Worker)employee).isLongerYear(((Worker)employee).getEmploymentLength(),1)),employee -> ((Worker)employee).isLongerYear(((Worker)employee).getEmploymentLength(),3),employee -> employee.getSalary().multiply(new BigDecimal(0.1))),HumanResourcesStatistics.seniorityBetweenOneAndThreeYears(employees,employee -> (((Worker)employee).isLongerYear(((Worker)employee).getEmploymentLength(),1)),employee -> ((Worker)employee).isLongerYear(((Worker)employee).getEmploymentLength(),3),employee -> employee.getSalary().multiply(new BigDecimal(0.1))));
      Assert.assertEquals(HumanResourcesStatistics.seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(employees,10,employee -> ((((Worker)employee).isLongerYear(((Worker)employee).getEmploymentLength(),2))),employee -> (((Worker)employee).isLongerYear(((Worker)employee).getEmploymentLength(),4)),employee -> (employee.getAge()>10)),HumanResourcesStatistics.seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(employees,10,employee -> ((((Worker)employee).isLongerYear(((Worker)employee).getEmploymentLength(),2))),employee -> (((Worker)employee).isLongerYear(((Worker)employee).getEmploymentLength(),4)),employee -> (employee.getAge()>10)));
   }

}

