package ch.heigvd.amt.application;

import ch.heigvd.amt.application.question.QuestionFacade;
import ch.heigvd.amt.domain.question.IQuestionRepository;
import ch.heigvd.amt.infrastructure.persistence.memory.InMemoryQuestionRepository;

public class ServiceRegistry{
    private static ServiceRegistry singleton;

    private static IQuestionRepository questionRepository;
    private static QuestionFacade questionFacade;

    public static ServiceRegistry getServiceRegistry(){
        if(singleton == null){
            singleton = new ServiceRegistry();
        }
        return singleton;
    }

    private ServiceRegistry(){
        singleton = this;
        questionRepository = new InMemoryQuestionRepository();
        questionFacade = new QuestionFacade(questionRepository);
    }

    public QuestionFacade getQuestionFacade(){return questionFacade;
    }
}