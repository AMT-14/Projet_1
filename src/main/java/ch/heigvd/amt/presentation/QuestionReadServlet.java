package ch.heigvd.amt.presentation;
import ch.heigvd.amt.model.*;
import java.util.Date;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet; import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; import javax.servlet.http.HttpServletResponse; import java.io.IOException;

@WebServlet(name = "QuestionReadServlet", urlPatterns = "/questionread")
public class QuestionReadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO faire en sorte que la question soit passée en paramètre?
        // TODO ajout des réponses
        Date date = new Date();
        User user = new User(3, "Pascal Delanonymat");
        Question question = new Question (user, "Sur les starting blocks?", date);
        request.setAttribute("question", question);
        request.getRequestDispatcher("/WEB-INF/views/questionReadView.jsp").forward(request, response);

    }
}