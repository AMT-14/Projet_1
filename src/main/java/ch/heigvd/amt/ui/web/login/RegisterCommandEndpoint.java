package ch.heigvd.amt.ui.web.login;

import ch.heigvd.amt.application.identitymng.IdentityManagementFacade;
import ch.heigvd.amt.application.identitymng.login.RegisterCommand;
import ch.heigvd.amt.application.identitymng.login.RegistrationFailedException;
import ch.heigvd.amt.application.ServiceRegistry;
import lombok.SneakyThrows;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "RegisterCommandServlet", urlPatterns = "/register.do")
public class RegisterCommandEndpoint extends HttpServlet {

    @Inject @Named("ServiceRegistry")
    ServiceRegistry serviceRegistry;

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        IdentityManagementFacade identityManagementFacade = serviceRegistry.getIdentityManagementFacade();
        request.getSession().removeAttribute("errors");
        // set content type header before accessing the Writer
        response.setContentType("text/html");

        // retrieve values from the form in the view
        RegisterCommand registerCommand = RegisterCommand.builder()
                .username(request.getParameter("username"))
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .email(request.getParameter("email"))
                .clearTextPassword(request.getParameter("password"))
                .build();

        try {
            identityManagementFacade.register(registerCommand);
            //request.getRequestDispatcher("/login.do").forward(request, response);
            return;
        } catch (RegistrationFailedException e) {
            request.getSession().setAttribute("errors", List.of(e.getMessage()));
            response.sendRedirect("/login");
            return;
        }
    }
}