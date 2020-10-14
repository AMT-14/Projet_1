package ch.heigvd.amt.ui.web.question;

import ch.heigvd.amt.application.ServiceRegistry;
import ch.heigvd.amt.application.question.ProposeQuestionCommand;
import ch.heigvd.amt.application.question.QuestionFacade;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SubmitQuestionCommandHandler", urlPatterns = "/submitQuestion.do")
public class ProposeQuestionCommandEndpoint extends HttpServlet{

    @Inject
    @Named("ServiceRegistry")
    ServiceRegistry serviceRegistry;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionFacade questionFacade = serviceRegistry.getQuestionFacade();
        ProposeQuestionCommand command = ProposeQuestionCommand.builder()
                .author("anonymous")
                .text(request.getParameter("text"))
                .build();

        questionFacade.proposeQuestion(command);
        response.sendRedirect("/questions");
    }
}