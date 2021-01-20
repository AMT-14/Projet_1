package ch.heigvd.amt.ui.web.profile;

import ch.heig.gamification.api.dto.Badge;
import ch.heig.gamification.api.dto.UserStat;
import ch.heigvd.amt.application.ServiceRegistry;
import ch.heigvd.amt.domain.user.User;

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


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ici faire du schmilblick pour get les userStats? attendre de pouvoir lancer l'api
        //request.setAttribute("userStats", userStats);
        request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
    }
}
