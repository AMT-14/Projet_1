package ch.heigvd.amt.application;

import ch.heigvd.amt.application.answer.AnswerFacade;
import ch.heigvd.amt.application.gamification.GamificationFacade;
import ch.heigvd.amt.application.identitymng.IdentityManagementFacade;
import ch.heigvd.amt.application.question.QuestionFacade;
import ch.heigvd.amt.application.vote.VoteFacade;
import ch.heigvd.amt.domain.answer.IAnswerRepository;
import ch.heigvd.amt.domain.question.IQuestionRepository;
import ch.heigvd.amt.domain.user.IUserRepository;
import ch.heigvd.amt.domain.vote.IVoteRepository;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import java.io.IOException;

@ApplicationScoped
@Named ("ServiceRegistry")
public class ServiceRegistry{

    @Inject @Named("JdbcUserRepository")
    IUserRepository userRepository;

    @Inject @Named("JdbcQuestionRepository")
    IQuestionRepository questionRepository;

    @Inject @Named("JdbcVoteRepository")
    IVoteRepository voteRepository;

    @Inject @Named("JdbcAnswerRepository")
    IAnswerRepository answerRepository;

    private VoteFacade voteFacade;
    private QuestionFacade questionFacade;
    private IdentityManagementFacade identityManagementFacade;
    private AnswerFacade answerFacade;
    private GamificationFacade gamificationFacade;

    @PostConstruct
    public void init() throws IOException {
        questionFacade = new QuestionFacade(questionRepository, voteRepository);
        identityManagementFacade = new IdentityManagementFacade(userRepository);
        voteFacade = new VoteFacade(voteRepository);
        answerFacade = new AnswerFacade((answerRepository));
        gamificationFacade = new GamificationFacade();
    }

    public GamificationFacade getPostEventFacade() {
        return gamificationFacade;
    }
    public QuestionFacade getQuestionFacade(){
        return questionFacade;
    }

    public IdentityManagementFacade getIdentityManagementFacade(){
        return identityManagementFacade;
    }

    public VoteFacade getVoteFacade() {
        return voteFacade;
    }

    public AnswerFacade getAnswerFacade(){return answerFacade;}
}