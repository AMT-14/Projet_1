// from the webcast
package Presentation;

import javax.imageio.spi.ServiceRegistry;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginCommandServlet", urlPatterns = "/login/do")
public class LoginCommandServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LoginCommand command = LoginCommand.builder()
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();

        Object loggedInUser = null; // should be PersonDTO at some point (cf webcast)

        try{
            loggedInUser = serviceRegistry.getIdentityFacade().login(command);
            request.getSession().setAttribute("loggedUser", loggedInUser);

            // place where we keep the original target URL, with that after logged in the user can finally  be redirected to their page
            String targetPageURL = (String)request.getSession().getAttribute("targetPageURL");
            targetPageURL = (targetPageURL != null) ? targetPageURL : "/questions";
            response.sendRedirect(targetPageURL);
            return;
        } catch (LoginFailedException e){
            request.setAttribute("errors", List.of("Invalid Login Credentials"));
            request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}
