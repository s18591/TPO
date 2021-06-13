package Classes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/result")
public class View extends HttpBase {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        Model model = (Model)req.getAttribute(MODEL);
        HttpSession session = req.getSession();
        Model model1 = (Model)session.getAttribute(MODEL);
        if(model == null || model1 == null){
            String ms = String.format("%s%s",req.getContextPath(),req.getServletPath());
            res.sendError(400,ms);
            return;
        }
        PrintWriter writer = res.getWriter();
        String resp = "attribute res: " + model.getResult() + " \nsession res: " + model1.getResult();
        writer.println(resp);
    }
}
