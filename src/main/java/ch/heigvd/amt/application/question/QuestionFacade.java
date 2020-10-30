package ch.heigvd.amt.application.question;

import ch.heigvd.amt.domain.question.IQuestionRepository;
import ch.heigvd.amt.domain.question.Question;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionFacade{
    private IQuestionRepository questionRepository;

    public QuestionFacade(IQuestionRepository questionRepository){this.questionRepository = questionRepository;}

    public void proposeQuestion(ProposeQuestionCommand command){
        Question submittedQuestion = Question.builder()
                .id(command.getId())
                .authorId(command.getAuthorId())
                .text(command.getText())
                .questionType(command.getType())
                .build();
        questionRepository.save(submittedQuestion);
    }

    public QuestionsDTO getQuestions(QuestionsQuery query){
        Collection<Question> allQuestions = questionRepository.find(query);

        List<QuestionsDTO.QuestionDTO> allQuestionsDTO = allQuestions.stream().map(question -> QuestionsDTO.QuestionDTO.builder()
            .id(question.getId().asString())
            .text(question.getText())
            .author(question.getAuthorId().asString())
            .build()).collect(Collectors.toList());

        return QuestionsDTO.builder()
                .questions(allQuestionsDTO)
                .build();
    }
}