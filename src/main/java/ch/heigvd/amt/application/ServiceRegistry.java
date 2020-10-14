package ch.heigvd.amt.application;

import ch.heigvd.amt.application.identitymng.IdentityManagementFacade;
import ch.heigvd.amt.application.question.QuestionFacade;
import ch.heigvd.amt.domain.question.IQuestionRepository;
import ch.heigvd.amt.domain.user.IUserRepository;
import ch.heigvd.amt.infrastructure.persistence.jdbc.JdbcQuestionRepository;
import ch.heigvd.amt.infrastructure.persistence.jdbc.JdbcUserRepository;
import ch.heigvd.amt.infrastructure.persistence.memory.InMemoryQuestionRepository;
import ch.heigvd.amt.infrastructure.persistence.memory.InMemoryUserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.annotation.PostConstruct;

@ApplicationScoped
public class ServiceRegistry{

    @Inject @Named("JdbcUserRepository")
    IUserRepository userRepository;

    @Inject @Named("JdbcQuestionRepository")
    IQuestionRepository questionRepository;

    private QuestionFacade questionFacade;

    private IdentityManagementFacade identityManagementFacade;

    @PostConstruct
    public void init() {
        questionFacade = new QuestionFacade(questionRepository);
        identityManagementFacade = new IdentityManagementFacade(userRepository);
    }

    public QuestionFacade getQuestionFacade(){
        return questionFacade;
    }

    public IdentityManagementFacade getIdentityManagementFacade(){
        return identityManagementFacade;
    }
}