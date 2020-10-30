package ch.heigvd.amt.domain.question;


import ch.heigvd.amt.domain.IRepository;

import java.util.Collection;

public interface  IQuestionRepository extends IRepository<Question, QuestionId> {
    public Collection<Question> find(QuestionId query);
}