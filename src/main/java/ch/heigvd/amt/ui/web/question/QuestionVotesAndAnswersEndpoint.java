package ch.heigvd.amt.ui.web.question;

        import ch.heigvd.amt.application.ServiceRegistry;
        import ch.heigvd.amt.application.answer.AnswerFacade;
        import ch.heigvd.amt.application.identitymng.authenticate.CurrentUserDTO;
        import ch.heigvd.amt.application.question.ProposeQuestionCommand;
        import ch.heigvd.amt.application.question.QuestionFacade;
        import ch.heigvd.amt.application.question.QuestionsDTO;
        import ch.heigvd.amt.application.vote.VoteFacade;
        import ch.heigvd.amt.domain.TextType;
        import ch.heigvd.amt.domain.question.QuestionId;

        import javax.inject.Inject;
        import javax.inject.Named;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

@WebServlet(name = "SubmitQuestionCommandHandler", urlPatterns = "/questionVotesAndAnswer")
public class QuestionVotesAndAnswersEndpoint extends HttpServlet {

    @Inject
    @Named("ServiceRegistry")
    ServiceRegistry serviceRegistry;

    private QuestionFacade questionFacade;
    private VoteFacade voteFacade;
    private AnswerFacade answerFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        questionFacade = serviceRegistry.getQuestionFacade();
        voteFacade = serviceRegistry.getVoteFacade();
        answerFacade = serviceRegistry.getAnswerFacade();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CurrentUserDTO user = (CurrentUserDTO) request.getSession().getAttribute("currentUser");
        String questionID = request.getParameter("questionID");
        QuestionId questionId = new QuestionId(questionID);


//        QuestionsDTO questionDTO = questionFacade.getQuestions(questionId);
//
//        int totalVotes = voteFacade.getTotalVotes(questionId, TextType.QUESTION);
//        questionDTO.totalVotes = totalVotes;

//        request.setAttribute("question", questionDTO);
        response.sendRedirect("/questions");
    }
}