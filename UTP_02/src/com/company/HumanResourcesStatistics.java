package com.company;

import java.math.BigDecimal;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.function.*;


public final class HumanResourcesStatistics {


    public static List<PayrollEntry> payroll(List<Employee> employees) {
        List<PayrollEntry> payrollEntries = new ArrayList<>();
        employees.forEach(e -> {
            if (e instanceof Worker) {
                Worker w = (Worker) e;
                payrollEntries.add(new PayrollEntry(e, e.getSalary(), w.getBonus()));
            } else {
                payrollEntries.add(new PayrollEntry(e, e.getSalary(), new BigDecimal(0)));
            }
        });
        return payrollEntries;
    }

    public static List<PayrollEntry> subordinatesPayroll(Manager manager) {
        List<PayrollEntry> payrollEntries = new ArrayList<>();
        manager.employees.forEach(e -> {
                    if (e instanceof Worker) {
                        Worker w = (Worker) e;
                        payrollEntries.add(new PayrollEntry(e, e.getSalary(), w.getBonus()));
                    } else
                        payrollEntries.add(new PayrollEntry(e, e.getSalary(), new BigDecimal(0)));
                }
        );
        return payrollEntries;
    }

    public static BigDecimal addTotal(BigDecimal b1, BigDecimal b2) {
        return b1.add(b2);
    }

    public static BigDecimal totalBonus = BigDecimal.ZERO;

    public static BigDecimal bonusTotal(List<Employee> employees) {
        employees.forEach(e -> {
            if (e instanceof Worker) {
                Worker w = (Worker) e;
                totalBonus = addTotal(totalBonus, w.getBonus());
            }

        });
        return totalBonus;
    }

    public int period(Period period) {
        return period.getDays() + period.getMonths() * 30 + period.getYears() * 365;
    }

    private static long longestWorker = 0;
    private static long longestTrainee = 0;

    public long longestSeniority(List<Employee> employees) {
        employees.forEach(e -> {
            if (e instanceof Worker || e instanceof Manager) {
                if (longestWorker < period(((Worker) e).getEmploymentLength())) {
                    longestWorker = period(((Worker) e).getEmploymentLength());
                }
            }
            if (e instanceof Trainee) {
                if (longestTrainee < period(((Trainee) e).getPracticeLength())) ;
                longestTrainee = period(((Trainee) e).getPracticeLength());
            }
        });
        if (longestTrainee > longestWorker) {
            return longestTrainee;
        } else {
            return longestWorker;
        }
    }

    static BigDecimal highest = new BigDecimal(0);

    public BigDecimal highestSalaryWithoutBonus(List<Employee> employees) {
        employees.forEach(e -> {
            if (highest.intValue() < e.getSalary().intValue()) {
                highest = e.getSalary();
            }
        });
        return highest;
    }

    static BigDecimal Highest = BigDecimal.ZERO;

    public BigDecimal highestSalaryWithBonus(List<Employee> employees) {
        List<PayrollEntry> payrollEntries = payroll(employees);
        BigDecimal highest = payrollEntries.get(0).get_salaryPlusBonus();
        Highest = highest;
        payrollEntries.forEach(p -> {
            if (Highest.intValue() < p.get_salaryPlusBonus().intValue()) {
                Highest = p.get_salaryPlusBonus();
            }
        });
        return Highest;
    }

    public List<Employee> surnameA(Manager manager) {
        List<Employee> employees = new ArrayList<>();
        manager.employees.forEach(e -> {
            if (e.getSurname().toCharArray()[0] == 'A') {
                employees.add(e);
            }
        });
        return employees;
    }

    public List<Employee> salaryBiggerThen1000(Manager manager) {
        List<Employee> employees = new ArrayList<>();
        manager.employees.forEach(e -> {
            if (e.getSalary().intValue() > 1000) {
                employees.add(e);
            }
        });
        return employees;
    }
    /// ...
    // rest of the methods specified in the assignment description
    // The best solution is to impleent the below features as static methods having a list of Employee as the first input argument.
    // In addition the list of arguments may be augmented with parameters required for the given feature.
    // najlepiej zaimplementowaж poniїsze metody jako statyczne, gdzie argumentem lista pracownikуw i odpowiednio dodatkowo to co wymagane w danym punkcie

    // (assignment 03)
    // methods:
    //
    // * search for Employees older than given employee and earning less than him
    //   wyszukaj osoby zatrudnione (Employee), ktуre s№ starsze od podanej innej zatrudnionej osoby oraz zarabiaj№ mniej od niej
    public static List<Employee> olderThenAndEarnMore(List<Employee> allEmployees, Employee employee, BiPredicate<Employee, Employee> biPredicate, BiPredicate<Employee, Employee> biPredicate1) {
        allEmployees.removeIf(e -> biPredicate.test(employee, e) && biPredicate1.test(employee, e));
        return allEmployees;
    }

    //
    // * search for Trainees whose practice length is longer than given number of days and raise their salary by 5%
    //   wyszukaj praktykantуw (Trainee), ktуrych praktyka jest dіuїsza od podanej liczby dni,
    //   a nastкpnie podnieњ ich uposaїenie o 5%
    public static List<Trainee> practiceLengthLongerThan(List<Employee> allEmployees, int daysCount, Predicate<Employee> predicate, Consumer<Employee> consumer) {
        List<Trainee> trainees = new ArrayList<>();
        allEmployees.removeIf(e -> !(e instanceof Trainee) || !predicate.test(e));
        allEmployees.forEach(consumer);
        allEmployees.forEach(employee -> {
            trainees.add((Trainee) employee);
        });
        return trainees;
    }

    //
    // * search for Workers whose seniority is longer than given number of months and give them bonus of 300 if their bonus is smaller
    //   wyszukaj pracownikуw o staїu dіuїszym niї podana liczba miesiкcy,
    //   a nastкpnie przyznaj im premiк w wysokoњci 300 jeњli ich premia jest niїsza
    //=================================================================================
    public static List<Worker> seniorityLongerThanMonth(List<Employee> allEmployees, int monthCount, Predicate<Employee> biPredicate, Consumer<Employee> consumer) {
        List<Worker> workers = new ArrayList<>();
        allEmployees.forEach(e -> {
            if( e instanceof Worker && biPredicate.test(e)){
                workers.add((Worker) e);
            }
        });
        workers.forEach(worker -> {
            if (worker.getBonus().intValue() < 300) {
                consumer.accept(worker);
            }
        });
        return workers;
    }
    //==========================================================================================================

    //
    // * search for Workers whose seniority is between 1 and 3 years and give them raise of salary by 10%
    //   wyszukaj pracownikуw o staїu pomiкdzy 1 a 3 lata i przyznaj im podwyїkк w wysokoњci 10%
    public static List<Worker> seniorityBetweenOneAndThreeYears(List<Employee> allEmployees, Predicate<Employee> biPredicate, Predicate<Employee> predicate1, Consumer<Employee> consumer) {
        List<Worker> workers = new ArrayList<>();
        allEmployees.forEach(e -> {
            if(allEmployees instanceof Worker && ((biPredicate.test(e) && !predicate1.test(e)))){
                workers.add((Worker) e);
                workers.forEach(consumer);
            }
        });
        return workers;
    }

    //
    // * search for Workers whose seniority is longer than the seniority of a given employee and earn less than him and align their salary with the given employee
    //   wyszukaj pracownikуw o staїu dіuїszym niї staї podanego pracownika i ktуrzy zarabiaj№ mniej od niego,
    //   nastкpnie zrуwnaj ich wynagrodzenie z wynagrodzeniem danego pracownika
    public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, Employee employee, BiPredicate<Employee, Employee> biPredicate, BiPredicate<Employee, Employee> biPredicate1, BiConsumer<Employee,Employee> consumer) {
        List<Worker> workers = new ArrayList<>();
        allEmployees.forEach(employee1 -> {
            if (allEmployees instanceof Worker && biPredicate.test(employee,employee1) && biPredicate1.test(employee,employee1)){
                workers.add((Worker)employee1);
                workers.forEach(worker -> consumer.accept(worker,employee));
            }
        });
        return workers;
    }

    //
    // * search for Workers whose seniority is between 2 and 4 years and whose age is greater than given number of years
    //   wyszukaj pracownikуw o staїu pomiкdzy 2 i 4 lata i starszych niї podana liczba lat
    public static List<Worker> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(List<Employee> allEmployees, int age, Predicate<Employee> predicate, Predicate<Employee> predicate1,Predicate<Employee> predicate2) {
        List<Worker> workers = new ArrayList<>();
            allEmployees.forEach(employee ->{
                if(allEmployees instanceof Worker && (predicate.test(employee)&&(!(predicate1.test(employee)))) && predicate2.test(employee)){
                    allEmployees.forEach(e -> {
                        workers.add((Worker) e);
                    });
                }
            });

        return workers;
    }
}