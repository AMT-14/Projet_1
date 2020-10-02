// from the webcast
package ch.heigvd.amt.ui.web.login;

import ch.heigvd.amt.application.identitymng.IdentityManagementFacade;
import ch.heigvd.amt.application.identitymng.authenticate.CurrentUserDTO;
import ch.heigvd.amt.application.identitymng.authenticate.LoginCommand;
import ch.heigvd.amt.application.identitymng.authenticate.LoginFailedException;
import ch.heigvd.amt.application.ServiceRegistry;
import ch.heigvd.amt.domain.user.User;
import lombok.SneakyThrows;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginCommandServlet", urlPatterns = "/login.do")
public class LoginCommandEndpoint extends HttpServlet {

    private ServiceRegistry serviceRegistery = ServiceRegistry.getServiceRegistry();
    private IdentityManagementFacade identityManagementFacade = serviceRegistery.getIdentityManagementFacade();

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().removeAttribute("errors");
        CurrentUserDTO currentUser = null;

        LoginCommand command = LoginCommand.builder()
                .username(request.getParameter("username"))
                .clearTextPassword(request.getParameter("password"))
                .build();

        try{
            currentUser = ServiceRegistry.getIdentityManagementFacade().authenticate(command);
            request.getSession().setAttribute("currentUser", currentUser);

            // place where we keep the original target URL, with that after logged in the user can finally  be redirected to their page
            String targetPageURL = (String)request.getSession().getAttribute("targetUrl");
            targetPageURL = (targetPageURL != null) ? targetPageURL : "/questions";
            response.sendRedirect(targetPageURL);
            return;
        }
        catch (LoginFailedException e) {
            request.setAttribute("errors", List.of("Invalid Login Credentials"));
            response.sendRedirect("/login");
        }
    }
}
