package TPO_10;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@WebService( //
        name = "IPerson", //
        targetNamespace = "http://TPO10/person" //
)

public class PersonService implements IPerson {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    public static void main(String... args) {
        Endpoint.publish(IPerson.URI, new PersonService());
        LOGGER.info("SERVICE STARTED");
    }

    public PersonService() {
    }
    public List<Person> sortBySurname(List<Person> people, String surname) {
        System.out.println("Surname");
        List<Person> res = new ArrayList<Person>();

        for(int i = 0; i<people.size(); i++) {
            if (people.get(i).surname.equals(surname)) {
                res.add(people.get(i));
            }
        }
        return res;
    }

    public List<Person> sortByBirthDate(List<Person> people, Date birthDate) {
        System.out.println("BirthDate");
        List<Person> res = new ArrayList<Person>();
        for(int i =0; i<people.size(); i++){
         if(people.get(i).birthDate.equals(birthDate)){
             res.add(people.get(i));
         }
        }
        return res;
    }
}
