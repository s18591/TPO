package com.company;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

abstract public class Person {

    // To implement an attribute means that you provide a backing field and
    // getter or optionally setter for read-write properties/attributes
    //
    // NO BACKING FIELDS SHOULD BE PROVIDED FOR DERIVED ATTRIBUTES
    // THOSE SHOULD BE COMPUTED ON-LINE
    //
    // attributes:
    // * first name (read-only)
    // * surname (read-only)
    // * birth date (read-only) --- date MUST BE represented by an instance of
    // type designed for date representation (either Date or LocalDate)
    //
    // * age (derived --- computed based on birth date) --- implemented as a
    // getter calculating the difference between the current date and birth date


    // (assignment 03)
    // methods:
    // * is older than other person
    // * is younger than other person
    // * compare age with other person's age
    String firstname;
    String surname;
    Date date;

    private final String _firstName; // backing field

    protected Person(String firstName) {
        _firstName = firstName;
    }

    public Person(String firstname, String surname, Date date) {
        this._firstName = firstname;
        this.surname = surname;
        this.date = date;
    }

    public boolean isOlder(Person p1) {
        if (p1.getAge() > getAge()) {
            return true;
        }
        return false;
    }

    public boolean isYounger(Person p1) {
        if (p1.getAge() < getAge()) {
            return true;
        }
        return false;
    }

    public int compageAge(Person person){
        if(isOlder(person)){ return  1;}
        if(isYounger(person)){ return -1;}
        return 0;

    }

    public String getFirstName() {
        return _firstName;
    }


    public String getFirstname() {
        return firstname;
    }

    public String get_firstName() {
        return _firstName;
    }

    public String getSurname() {
        return surname;
    }

    public Date getDate() {
        return date;
    }

    public int getAge() {
        return LocalDate.now().getYear() - date.getYear();
    }

    @Override
    public String toString() {
        return " surname = " + surname + " Name = " + _firstName + "\n";
    }
}