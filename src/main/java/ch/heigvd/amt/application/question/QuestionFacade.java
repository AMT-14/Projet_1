package ch.heigvd.amt.application.question;

import ch.heigvd.amt.domain.question.IQuestionRepository;
import ch.heigvd.amt.domain.question.Question;
import ch.heigvd.amt.domain.vote.IVoteRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionFacade{
    private IVoteRepository voteRepository;
    private IQuestionRepository questionRepository;

    public QuestionFacade(IQuestionRepository questionRepository, IVoteRepository voteRepository){
        this.questionRepository = questionRepository;
        this.voteRepository = voteRepository;
    }

    public void proposeQuestion(ProposeQuestionCommand command){
        Question submittedQuestion = Question.builder()
                .id(command.getId())
                .authorId(command.getAuthorId())
                .text(command.getText())
                .questionType(command.getType())
                .build();
        questionRepository.save(submittedQuestion);
        // TODO HERE call postEvent
    }

    public QuestionsDTO getQuestions(QuestionsQuery query){
        Collection<Question> allQuestions = questionRepository.find(query);

        List<QuestionsDTO.QuestionDTO> allQuestionsDTO = allQuestions.stream().map(question -> QuestionsDTO.QuestionDTO.builder()
            .id(question.getId().asString())
            .text(question.getText())
            .author(question.getAuthorId().asString())
            .build()).collect(Collectors.toList());

        for(QuestionsDTO.QuestionDTO question : allQuestionsDTO) {
            question.updateTotalVotes(voteRepository);
        }



        return QuestionsDTO.builder()
                .questions(allQuestionsDTO)
                .build();
    }
}