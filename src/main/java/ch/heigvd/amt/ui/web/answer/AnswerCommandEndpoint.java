package ch.heigvd.amt.ui.web.answer;

import ch.heigvd.amt.application.ServiceRegistry;
import ch.heigvd.amt.application.answer.AnswerCommand;
import ch.heigvd.amt.application.answer.AnswerFacade;
import ch.heigvd.amt.application.identitymng.authenticate.CurrentUserDTO;
import ch.heigvd.amt.domain.answer.Answer;
import ch.heigvd.amt.domain.question.QuestionId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AnswerCommandServlet", urlPatterns = "/answer.do")
public class AnswerCommandEndpoint extends HttpServlet {

    @Inject
    ServiceRegistry registry;
    AnswerFacade facade;

    @Override
    public void init() throws ServletException{
        super.init();
        facade = registry.getAnswerFacade();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        CurrentUserDTO currentUser = (CurrentUserDTO)request.getSession().getAttribute("currentUser");

        AnswerCommand command = AnswerCommand.builder()
                .authorId(currentUser.getId())
                .questionID(new QuestionId(request.getParameter("questionID")))
                .text(request.getParameter("text"))
                .build();

        facade.save(command);
        response.sendRedirect(request.getContextPath() + "/questions?questionID=" + request.getParameter("questionID"));
    }
}
