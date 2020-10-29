package ch.heigvd.amt.application.question;

import ch.heigvd.amt.domain.question.QuestionId;
import ch.heigvd.amt.domain.question.QuestionType;
import ch.heigvd.amt.domain.user.UserId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class ProposeQuestionCommand {
    @Builder.Default
    private UserId authorId = null;

    @Builder.Default
    private QuestionId id = null;

    @Builder.Default
    private String text = null;

    @Builder.Default
    private QuestionType type = QuestionType.OTHER;
}