package ch.heigvd.amt.infrastructure.persistence.memory;

import ch.heigvd.amt.application.question.QuestionsQuery;
import ch.heigvd.amt.domain.question.IQuestionRepository;
import ch.heigvd.amt.domain.question.Question;
import ch.heigvd.amt.domain.question.QuestionId;
import ch.heigvd.amt.domain.question.QuestionType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryQuestionRepository extends InMemoryRepository<Question, QuestionId> implements IQuestionRepository{

    @Override
    public Collection<Question> find(QuestionsQuery query){
        if(query != null && query.isSafeForWork()){
            return findAll().stream()
                    .filter(question -> question.getQuestionType() != QuestionType.NOT_SAFE_FOR_WORK)
                    .collect(Collectors.toList());
        }
        return findAll();
    }
}