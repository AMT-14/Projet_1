package ch.heigvd.amt.application.question;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class QuestionsQuery{
    @Builder.Default
    private boolean safeForWork = true;
}