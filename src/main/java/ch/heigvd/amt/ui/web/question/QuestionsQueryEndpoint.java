package ch.heigvd.amt.ui.web.question;

import ch.heigvd.amt.application.ServiceRegistry;
import ch.heigvd.amt.application.question.QuestionFacade;
import ch.heigvd.amt.application.question.QuestionsDTO;
import ch.heigvd.amt.application.question.QuestionsQuery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="QuestionsPageHandler", urlPatterns = "/questions")
public class QuestionsQueryEndpoint extends HttpServlet{
    private ServiceRegistry serviceRegistry;
    private QuestionFacade questionFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        serviceRegistry = ServiceRegistry.getServiceRegistry();
        questionFacade = serviceRegistry.getQuestionFacade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionsDTO questionsDTO = questionFacade.getQuestions(QuestionsQuery.builder()
        .safeForWork(false)
        .build());
        request.setAttribute("questions", questionsDTO);
        request.getRequestDispatcher("/WEB-INF/views/questions.jsp").forward(request, response);
    }
}