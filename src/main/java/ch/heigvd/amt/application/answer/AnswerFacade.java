package ch.heigvd.amt.application.answer;

import ch.heigvd.amt.domain.answer.Answer;
import ch.heigvd.amt.domain.answer.IAnswerRepository;
import ch.heigvd.amt.domain.question.QuestionId;

import java.util.List;

public class AnswerFacade {
    private IAnswerRepository repository;

    public AnswerFacade(IAnswerRepository repository){
        this.repository = repository;
    }

    public void save(AnswerCommand command){
        repository.save(Answer.builder()
                .authorId(command.getAuthorId())
                .questionId(command.getQuestionID())
                .text(command.getText())
                .build());

    }

    public List<Answer> getOtherAnswers(QuestionId id){
        return repository.findAnswersToThisQuestion(id);
    }
}
