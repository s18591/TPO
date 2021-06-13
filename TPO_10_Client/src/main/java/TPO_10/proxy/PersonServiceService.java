
package TPO_10.proxy;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.3
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PersonServiceService", targetNamespace = "http://TPO10/person", wsdlLocation = "http://localhost:8080/person?wsdl")
public class PersonServiceService
    extends Service
{

    private final static URL PERSONSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException PERSONSERVICESERVICE_EXCEPTION;
    private final static QName PERSONSERVICESERVICE_QNAME = new QName("http://TPO10/person", "PersonServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/person?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PERSONSERVICESERVICE_WSDL_LOCATION = url;
        PERSONSERVICESERVICE_EXCEPTION = e;
    }

    public PersonServiceService() {
        super(__getWsdlLocation(), PERSONSERVICESERVICE_QNAME);
    }

    public PersonServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), PERSONSERVICESERVICE_QNAME, features);
    }

    public PersonServiceService(URL wsdlLocation) {
        super(wsdlLocation, PERSONSERVICESERVICE_QNAME);
    }

    public PersonServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PERSONSERVICESERVICE_QNAME, features);
    }

    public PersonServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PersonServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IPerson
     */
    @WebEndpoint(name = "IPersonPort")
    public IPerson getIPersonPort() {
        return super.getPort(new QName("http://TPO10/person", "IPersonPort"), IPerson.class);
    }

    /**
     * 
     * @param features
     *     A list of {&#064;link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the &lt;code&gt;features&lt;/code&gt; parameter will have their default values.
     * @return
     *     returns IPerson
     */
    @WebEndpoint(name = "IPersonPort")
    public IPerson getIPersonPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://TPO10/person", "IPersonPort"), IPerson.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PERSONSERVICESERVICE_EXCEPTION!= null) {
            throw PERSONSERVICESERVICE_EXCEPTION;
        }
        return PERSONSERVICESERVICE_WSDL_LOCATION;
    }

}