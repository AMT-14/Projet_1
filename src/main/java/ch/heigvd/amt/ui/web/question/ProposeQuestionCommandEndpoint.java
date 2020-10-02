package ch.heigvd.amt.ui.web.question;

import ch.heigvd.amt.application.ServiceRegistry;
import ch.heigvd.amt.application.question.ProposeQuestionCommand;
import ch.heigvd.amt.application.question.QuestionFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name =  "SubmitQuestionCommandHandler", urlPatterns = "/submitQuestion.do")
public class ProposeQuestionCommandEndpoint extends HttpServlet{

    private ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
    private QuestionFacade questionFacade = serviceRegistry.getQuestionFacade();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProposeQuestionCommand command = ProposeQuestionCommand.builder()
                .author("anonymous")
                .text(request.getParameter("text"))
                .build();

        questionFacade.proposeQuestion(command);
        response.sendRedirect("/questions");
    }
}