package ch.heigvd.amt.ui.web.login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "RegisterCommandServlet", urlPatterns = "/register")
public class RegisterCommandEndpoint extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // set content type header before accessing the Writer
        response.setContentType("text/html");
        // retrieve values from the form in the view
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        // check completion
        if(email.isEmpty() || password.isEmpty()){
            // if at least one is missing, we need to redirect to the register page to have a correct one
            RequestDispatcher reqDispatch = request.getRequestDispatcher("registerView.jsp");
            reqDispatch.include(request, response);
        } else {
            //else it's a success, we can redirect to a success page
            RequestDispatcher reqDispatch = request.getRequestDispatcher("registerViewSuccess.jsp");
            reqDispatch.forward(request, response);
        }
    }
}