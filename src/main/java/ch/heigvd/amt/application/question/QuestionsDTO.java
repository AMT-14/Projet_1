package ch.heigvd.amt.application.question;

import ch.heigvd.amt.domain.TextType;
import ch.heigvd.amt.domain.question.QuestionId;
import ch.heigvd.amt.domain.vote.IVoteRepository;
import ch.heigvd.amt.infrastructure.persistence.jdbc.JdbcVoteRepository;
import lombok.*;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class QuestionsDTO{

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class QuestionDTO{

        private String id;
        private String text;
        private String author;

        @Setter
        private int totalVotes;

        public void updateTotalVotes(IVoteRepository repository) {
            totalVotes = repository.totalVotes(new QuestionId(id), TextType.QUESTION);
        }
    }

    @Singular
    private List<QuestionDTO> questions;
}