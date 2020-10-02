package ch.heigvd.amt.application.page;


import ch.heigvd.amt.domain.question.Post;
import ch.heigvd.amt.domain.question.Question;
import ch.heigvd.amt.domain.user.User;

import java.util.Date;
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
        Date date = new Date();
        User user = new User(4, "Olivier Liechti");
        Post post = new Question(user, "La question elle est vite repondue", date);
        HomePage p = new HomePage(true, "Hello World ", "No rules here cowboy, it's the Far West", temp, post);

        request.setAttribute("HomePage", p);
        request.getRequestDispatcher("/WEB-INF/views/homePageView.jsp").forward(request,response);

    }
}
