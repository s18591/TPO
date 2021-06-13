package TPO_10;

import TPO_10.proxy.IPerson;
import TPO_10.proxy.Person;
import TPO_10.proxy.PersonServiceService;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.junit.Assert;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

public class Testing {


    private static final Logger LOGGER = Logger.getAnonymousLogger();

    static {
        System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dumpTreshold", "999999");
    }

    @org.junit.Test
    public void main() throws MalformedURLException, DatatypeConfigurationException {

        List<Person> eq = new ArrayList<Person>();
        List<Person> people = new ArrayList<Person>();
        Person person = new Person();
        Date date = new Date(2000, 11, 11);
        Date date1 = new Date(1990, 2, 28);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date1);
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

        GregorianCalendar b = new GregorianCalendar();
        c.setTime(date);
        XMLGregorianCalendar date4 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        person.setFirstName("Jaroslaw");
        person.setSurname("Vovkotrub");
        person.setBirthDate(date2);

        Person person1 = new Person();
        person1.setFirstName("Jaruto");
        person1.setSurname("Gerosz");
        person1.setBirthDate(date2);

        Person person2 = new Person();
        person2.setFirstName("Licht");
        person2.setSurname("Gerosz");
        person2.setBirthDate(date4);

        Person person3 = new Person();
        person3.setFirstName("Bart");
        person3.setSurname("Simpson");
        person3.setBirthDate(date4);

        people.add(person);
        people.add(person1);
        people.add(person2);
        people.add(person3);

        eq.add(person1);
        eq.add(person2);

        URL wsdl = new URL("http://localhost:8080/person?wsdl");
        PersonServiceService service = new PersonServiceService(wsdl);
        IPerson proxy = service.getIPersonPort();
        List<Person> res = proxy.sortByBirthDate(people, date4);
        List<Person> res2 = proxy.sortBySurname(people, person1.getSurname());
        Assert.assertTrue(res2.get(0).getSurname().equals(person1.getSurname()));
        Assert.assertEquals(res.get(0).getBirthDate(),date4);
        Assert.assertNotNull(eq.get(0));
    }
}
