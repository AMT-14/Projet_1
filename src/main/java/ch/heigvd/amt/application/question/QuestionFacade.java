package ch.heigvd.amt.application.question;

import ch.heig.gamification.ApiException;
import ch.heigvd.amt.application.gamification.EventType;
import ch.heigvd.amt.application.gamification.GamificationFacade;
import ch.heigvd.amt.domain.question.IQuestionRepository;
import ch.heigvd.amt.domain.question.Question;
import ch.heigvd.amt.domain.vote.IVoteRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionFacade{
    private IVoteRepository voteRepository;
    private IQuestionRepository questionRepository;
    private GamificationFacade gamificationFacade;

    public QuestionFacade(IQuestionRepository questionRepository, IVoteRepository voteRepository, GamificationFacade gamificationFacade) {
        this.gamificationFacade = gamificationFacade;
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
        try {
            gamificationFacade.PostEvent(command.getId().toString(), EventType.EVENT_QUESTION, null);
        } catch (ApiException e) {
            System.out.println("unable to post question event to the gamification api");
        }

        questionRepository.save(submittedQuestion);
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