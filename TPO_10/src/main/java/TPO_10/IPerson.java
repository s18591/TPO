package TPO_10;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Date;
import java.util.List;
@WebService( //
        name = "IPerson", //
        targetNamespace = "http://TPO10/person" //
)

public interface IPerson {
    public static final String URI = "http://localhost:8080/person";
    @WebMethod(action = "http://TPO10/person/sortBySurname")
    public List<Person> sortBySurname(List<Person> people,String surname);
    @WebMethod(action = "http://TPO10/person/sortByBirthDate")
    public List<Person> sortByBirthDate(List<Person> people, Date birthDate);
}
