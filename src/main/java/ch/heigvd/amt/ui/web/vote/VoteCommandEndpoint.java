package ch.heigvd.amt.ui.web.vote;

import ch.heigvd.amt.application.ServiceRegistry;
import ch.heigvd.amt.application.identitymng.authenticate.CurrentUserDTO;
import ch.heigvd.amt.application.vote.VoteCommand;
import ch.heigvd.amt.application.vote.VoteDTO;
import ch.heigvd.amt.application.vote.VoteFacade;
import ch.heigvd.amt.application.vote.VoteUpdateCommand;
import ch.heigvd.amt.domain.Id;
import ch.heigvd.amt.domain.TextType;
import ch.heigvd.amt.domain.question.QuestionId;
import ch.heigvd.amt.domain.vote.Vote;
import ch.heigvd.amt.domain.vote.VoteId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name= "VoteCommandServlet", urlPatterns = "/vote.do")
public class VoteCommandEndpoint extends HttpServlet {

    @Inject
    ServiceRegistry registry;
    VoteFacade facade;

    @Override
    public void init() throws ServletException{
        super.init();
        facade = registry.getVoteFacade();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        CurrentUserDTO currentUser = (CurrentUserDTO) request.getSession().getAttribute("currentUser");
        String questionID = request.getParameter("questionID");
        String voteID = request.getParameter("voteID");
        String valueString = request.getParameter("value");
        Vote.VoteValue value = null;

        if(valueString.equals("DOWN")){
            value = Vote.VoteValue.DOWN;
        } else {
            value = Vote.VoteValue.UP;
        }

        VoteDTO vote = null;
        Id objectVoted = null;
        TextType textType;


        if(request.getParameter("objectVotedType").equals("question")){
            objectVoted = new QuestionId(questionID);
            textType = TextType.QUESTION;
        }
        if(!(voteID.isEmpty())){
            vote = facade.getVote(new VoteId(voteID), textType);
        }

        if(vote == null){
            VoteCommand command = VoteCommand.builder()
                    .voter(currentUser.getId())
                    .objectVoted(objectVoted)
                    .value(value)
                    .build();

            facade.vote(command);
        } else {
            // update existing vote
            if(value != vote.getValue()){
                facade.modifyVote(VoteUpdateCommand.builder()
                        .id(new VoteId(voteID))
                        .voter(currentUser.getId())
                        .objectVoted(objectVoted)
                        .value(value)
                        .build());
            } else {
                // vote where identical, remove one
                facade.removeVote(new VoteId(vote.getId()));
            }
        }

        response.sendRedirect(request.getContextPath() + "/questionFull?questionID=" + questionID);

    }
}
