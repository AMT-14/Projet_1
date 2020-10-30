package ch.heigvd.amt.ui.web.filter;


import ch.heigvd.amt.application.identitymng.authenticate.CurrentUserDTO;
import ch.heigvd.amt.domain.user.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AutorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest serveletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) serveletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Static ressource or login pae should not be restricted to connected users
        if(isResourcePublic(request.getRequestURI())){
            chain.doFilter(serveletRequest, servletResponse);
            return;
        }

        CurrentUserDTO loggedInUser = (CurrentUserDTO) request.getSession().getAttribute("currentUser");

        // if other ressources are not public, we need to redirect on the login page
        if (loggedInUser == null){
            // save original target of the user, to come back at it after log in
            String targetURL = request.getRequestURI();
            if(request.getQueryString() != null){targetURL += "?" + request.getQueryString(); }

            request.getSession().setAttribute("targetURL", targetURL);

            // make him go to login page
            ((HttpServletResponse) servletResponse).sendRedirect("/login");
            return;
        }

        chain.doFilter(serveletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    boolean isResourcePublic(String URI){
        if(URI.startsWith("/login") || URI.startsWith("/logout") || URI.startsWith("/assets") || URI.startsWith("/register") || URI.startsWith("/bootstrap") || URI.startsWith("/browse")){
            return true;
        } else {
            return false;
        }
    }
}
