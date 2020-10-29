package ch.heigvd.amt.domain.answer;

import ch.heigvd.amt.domain.IRepository;
import ch.heigvd.amt.domain.question.QuestionId;

import java.util.Collection;
import java.util.List;

public interface IAnswerRepository extends IRepository<Answer, AnswerId> {
    List<Answer> findAnswersToThisQuestion(QuestionId id);
}
