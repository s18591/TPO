package com.company.Tests;

import com.company.Container;
import com.company.Sample.Company;
import com.company.Sample.Person;
import com.company.Sample.WeatherOnPlanet;
import com.sun.xml.internal.ws.policy.AssertionSet;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class UnitTest {
    @Test
    void companyTest() {
        Company company = new Company(5000);
        System.out.println("AggregateShareOfCompany : " + company.aggregate(10d));
        System.out.println("DeepCloneOfCompany : " + company.deepClone().getShare());
        System.out.println("===========================================");
    }

    @Test
    void weatherOnPlanetTest() {
        WeatherOnPlanet weatherOnPlanet = new WeatherOnPlanet(40);
        System.out.println("AggregateWeatherOnPlanet : " + weatherOnPlanet.aggregate(2.0));
        System.out.println("DeepCloneOfWeatherOnPlanet : " + weatherOnPlanet.deepClone().getTemperature());
        System.out.println("============================================");
    }

    @Test
    void personTest() {
        Person person = new Person(18);
        System.out.println("AggregatePerson : " + person.aggregate(50));
        System.out.println("DeepClonePerson : " + person.deepClone().age());
        System.out.println("===============================================");
    }

    @Test
    void personContainer() {
        System.out.println("PersonContainer");
        List<Person> people = new ArrayList<>();
        people.add(new Person(20));
        people.add(new Person(30));
        people.add(new Person(10));
        Container<Person, Integer> container = new Container<>(people);
        System.out.println(container.elements());
        System.out.println("AggregateAllOfElements : " + container.aggregateAllElements());
        Assert.assertNotEquals(container.elements().get(0),container.cloneElementAtIndex(0));
       // System.out.println("CloneElementAtIndex : " + checkPerson(container.elements().get(0), container.cloneElementAtIndex(0)));
        System.out.println("=============================");
    }

    @Test
    void CompanyContainer() {
        System.out.println("CompanyContainer");
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(5000));
        companies.add(new Company(6000));
        companies.add(new Company(4000));
        Container<Company, Double> companyDoubleContainer = new Container<>(companies);
        System.out.println(companyDoubleContainer.elements());
        System.out.println("AggregateAllOfElements : " + companyDoubleContainer.aggregateAllElements());
        //System.out.println("CloneElementAtIndex : " + checkCompany(companyDoubleContainer.elements().get(0), companyDoubleContainer.cloneElementAtIndex(0)));
        Assert.assertNotEquals(companyDoubleContainer.elements().get(0),companyDoubleContainer.cloneElementAtIndex(0));
        System.out.println("=====================================");
    }

    @Test
    void WeatherOnPlanetContainer() {
        System.out.println("WeatherOnPlanetContainer");
        List<WeatherOnPlanet> weatherOnPlanets = new ArrayList<>();
        weatherOnPlanets.add(new WeatherOnPlanet(27));
        weatherOnPlanets.add(new WeatherOnPlanet(33));
        weatherOnPlanets.add(new WeatherOnPlanet(-4));
        Container<WeatherOnPlanet, Double> weatherOnPlanetDoubleContainer = new Container<>(weatherOnPlanets);
        System.out.println(weatherOnPlanetDoubleContainer.elements());
        Assert.assertNotEquals(weatherOnPlanetDoubleContainer.elements().get(0), weatherOnPlanetDoubleContainer.cloneElementAtIndex(0));
        System.out.println("AggregateAllElements : " + weatherOnPlanetDoubleContainer.aggregateAllElements());
//        System.out.println("CloneElementsAtIndex : " + checkWeatherOnPlanet(weatherOnPlanetDoubleContainer.elements().get(0), weatherOnPlanetDoubleContainer.cloneElementAtIndex(0)));
        System.out.println("=======================================");
    }


//    boolean checkPerson(Person p1, Person p2) {
//        if (p1.age() == p2.age()) {
//            return true;
//        }
//        return false;
//    }
//
//    boolean checkCompany(Company c1, Company c2) {
//        if (c1.getShare() == c2.getShare()) {
//            return true;
//        }
//        return false;
//    }
//
//    boolean checkWeatherOnPlanet(WeatherOnPlanet w1, WeatherOnPlanet w2) {
//        if (w1.getTemperature() == w2.getTemperature()) {
//            return true;
//        }
//        return false;
   // }
}