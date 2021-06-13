package TPO_10;

import java.util.Date;

public class Person {

    public String firstName;
    public String surname;
    public Date birthDate;

     public Person(){
     }
    public Person(String firstName, String surname, Date birthDate) {
        this.firstName = firstName;
        this.surname = surname;
        this.birthDate = birthDate;
    }
}
