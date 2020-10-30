package ch.heigvd.amt.application.question;

import lombok.*;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class QuestionsDTO{
    public int totalVotes;

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class QuestionDTO{
        private String text;
        private String author;

        @Setter public int totalVotes;
    }

    @Singular
    private List<QuestionDTO> questions;
}