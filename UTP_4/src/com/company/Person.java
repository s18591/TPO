package com.company;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Date;

public class Person implements Comparable<Person> {

    private final String firstName;
    private final String surname;
    private final Date birthdate;

    public Person(String firstName, String surname, Date birthdate) {
        this.firstName = firstName;
        this.surname = surname;
        this.birthdate = birthdate;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }


    public void seriazile(DataOutputStream output) {
        try {
            output.writeUTF(firstName);
            output.writeUTF(surname);
            DateUtility.serialize(birthdate, output);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static Person deserialize(DataInputStream input){
        try{
            String firstName = input.readUTF();
            String surname = input.readUTF();
            Date birthDate = DateUtility.deserialize(input);
            return new Person(firstName,surname,birthDate);
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
    return null;}

    @Override
    public int compareTo(Person otherPerson) {
        int res = this.getSurname().compareTo(otherPerson.getSurname());
        if(res != 0){
            return res;
        }
        res = this.getFirstName().compareTo(otherPerson.getFirstName());
        if(res != 0){
            return res;
        }
        return this.birthdate.compareTo(otherPerson.birthdate);
    }
}