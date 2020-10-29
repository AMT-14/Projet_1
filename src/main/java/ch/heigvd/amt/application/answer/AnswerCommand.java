package ch.heigvd.amt.application.answer;

import ch.heigvd.amt.domain.question.QuestionId;
import ch.heigvd.amt.domain.user.UserId;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AnswerCommand {
    private UserId authorId;
    private QuestionId questionID;
    private String text;

}
