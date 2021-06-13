package Classes;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/Add")
public class Add extends HttpBase {
    private static final String NUM_PATTERN = "^[0-9]+$";
    private static final Pattern REGEX = Pattern.compile(NUM_PATTERN);
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BigInteger num1 = parameter(req, "num1");
        BigInteger num2 = parameter(req, "num2");
        Model model = Logic.add(num1,num2);
        req.setAttribute(MODEL,model);
        HttpSession session = req.getSession();
        session.setAttribute(MODEL,model);
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/result");
        dispatcher.forward(req,res);
    }

    private static BigInteger parameter(HttpServletRequest req, String name) {
        String parameter = (String) req.getParameter(name);
        if (parameter == null) {
            return null;
        }
        Matcher matcher = REGEX.matcher(parameter);
        if(matcher.matches()){
            return new BigInteger(parameter);
        }
        return null;

    }
}

