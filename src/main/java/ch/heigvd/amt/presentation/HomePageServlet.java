package ch.heigvd.amt.presentation;

import ch.heigvd.amt.model.HomePage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "HomePageServlet", urlPatterns = "/home")
public class HomePageServlet extends javax.servlet.http.HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(3);
        temp.add(40);
        HomePage p = new HomePage(true, "Hello World ", "No rules here cowboy, it's the Far West", temp);

        request.setAttribute("statistics", temp);
        request.getRequestDispatcher("/WEB-INF/views/homePageView.jsp.jsp").forward(request,response);
    }
}
