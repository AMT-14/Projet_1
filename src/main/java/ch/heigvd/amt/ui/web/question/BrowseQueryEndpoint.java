package ch.heigvd.amt.ui.web.question;

import ch.heigvd.amt.application.ServiceRegistry;
import ch.heigvd.amt.application.question.QuestionFacade;
import ch.heigvd.amt.application.question.QuestionsDTO;
import ch.heigvd.amt.application.question.QuestionsQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="BrowseHandler", urlPatterns = "/browse")
public class BrowseQueryEndpoint extends HttpServlet{

    @Inject @Named("ServiceRegistry")
    ServiceRegistry serviceRegistry;

    private QuestionFacade questionFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        questionFacade = serviceRegistry.getQuestionFacade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionsDTO questionsDTO = questionFacade.getQuestions(QuestionsQuery.builder()
                .safeForWork(false)
                .build());
        request.setAttribute("questions", questionsDTO);
        request.getRequestDispatcher("/WEB-INF/views/browse.jsp").forward(request, response);
    }
}