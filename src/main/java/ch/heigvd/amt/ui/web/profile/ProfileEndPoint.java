package ch.heigvd.amt.ui.web.profile;

import ch.heig.gamification.ApiException;
import ch.heig.gamification.api.dto.UserStat;
import ch.heigvd.amt.application.ServiceRegistry;
import ch.heigvd.amt.application.gamification.GamificationFacade;
import ch.heigvd.amt.application.identitymng.authenticate.CurrentUserDTO;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="ProfilePageHandler", urlPatterns = "/profile")
public class ProfileEndPoint extends HttpServlet {

    @Inject @Named("ServiceRegistry")
    ServiceRegistry serviceRegistry;

    private GamificationFacade gamificationFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        gamificationFacade = serviceRegistry.getPostEventFacade();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ici faire du schmilblick pour get les userStats? attendre de pouvoir lancer l'api (docker-compose working?)
        // VOIR COMMENT ON GARDE DANS L'APPLI LE USER PSEUDO? UserStat userStats = postEventFacade.getApi().getUser(ACOMPLETER(STRING))
        CurrentUserDTO user = (CurrentUserDTO) request.getSession().getAttribute("currentUser");
        try {
            UserStat userStats = gamificationFacade.getUserStats(user.getId().asString());
            request.setAttribute("userStats", userStats);
        } catch (ApiException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
    }
}
