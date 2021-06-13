package TPO_10;

import javax.jws.WebMethod;
import javax.jws.WebService;


public interface ICalculator {

	public static final String URI = "http://localhost:8080/sample";

	@WebMethod(action = "http://glowacki.eu/calculator/add")
	int add(int component1, int component2);

	@WebMethod(action = "http://glowacki.eu/calculator/subtract")
	int subtract(int minuend, int subtrahend);
}