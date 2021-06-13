package com.company;

import com.company.Comparators.BirthdateComparator;
import com.company.Comparators.FirstNameComparator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public final class PersonDatabase {


    private static final Comparator<Person> BYALL = Comparator.naturalOrder();

    private static final Comparator<Person> BYFIRSTNAMECOMP = new FirstNameComparator();

    private static final Comparator<Person> BYBIRTHDAYCOMP = new BirthdateComparator();



    private final List<Person> bySurnameAndFirstNameAndBirthdate;
    private final List<Person> byBirthdate;
    private final List<Person> byFirstName;
    private final Map<Date, List<Person>> searchByBirthdate;

    public PersonDatabase(File file) throws IOException {
        this(InputParser.parse(file));
    }

    public PersonDatabase(List<Person> list) {
        bySurnameAndFirstNameAndBirthdate = list;
        byFirstName = new ArrayList<>(list);
        byBirthdate = new ArrayList<>(list);
        bySurnameAndFirstNameAndBirthdate.sort(BYALL);
        byFirstName.sort(BYFIRSTNAMECOMP);
        byBirthdate.sort(BYBIRTHDAYCOMP);
        searchByBirthdate = list.stream().collect(
                Collectors.groupingBy(
                        Person::getBirthdate, TreeMap::new, Collectors.mapping(p -> p, Collectors.toList())));

    }

    public List<Person> sortedByFirstName() {
        return byFirstName;
    }

    public List<Person> sortedBySurnameFirstNameAndBirthdate() {
        return bySurnameAndFirstNameAndBirthdate;
    }

    public List<Person> sortedByBirthdate() {
        return byBirthdate;
    }

    public List<Person> bornOnDay(Date date) {
        return searchByBirthdate.get(date);
    }

    public static PersonDatabase deserialize(DataInputStream input){
        try{
            int size = input.readInt();
            List<Person> people = new ArrayList<>();
            for(int i =0; input.available() > 0 && i< size; i++){
                Person person = Person.deserialize(input);
                people.add(person);
            }
            return  new PersonDatabase(people);
        }catch (Throwable e){
            e.printStackTrace();
        }
        return null;
    }
    public void serialize(DataOutputStream output){
         try{
             output.writeInt(bySurnameAndFirstNameAndBirthdate.size());
             bySurnameAndFirstNameAndBirthdate.forEach(person -> person.seriazile(output));
         }catch (Throwable e){
             e.printStackTrace();
         }
    }
}