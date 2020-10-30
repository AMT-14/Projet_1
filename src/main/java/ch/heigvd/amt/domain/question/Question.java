package ch.heigvd.amt.domain.question;


import ch.heigvd.amt.domain.IEntity;
import ch.heigvd.amt.domain.Id;
import ch.heigvd.amt.domain.user.User;
import ch.heigvd.amt.domain.user.UserId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class Question implements IEntity<Question, QuestionId> {

    @Setter(AccessLevel.NONE)
    private QuestionId id;

    private UserId authorId;

    private String text;

    @Setter(AccessLevel.NONE)
    private QuestionType questionType;

    public void categorizeAs(QuestionType questionType){this.questionType = questionType;}

    @Override
    public Question deepClone() {

        return this.toBuilder()
                .id(new QuestionId(id.asString()))
                .build();
    }

    public static  class  QuestionBuilder{
        public Question build(){
            if(id == null){
                id = new QuestionId();
            }


            if(authorId == null){
                throw new IllegalArgumentException("Question needs an author");
            }

            if(questionType == null){
                questionType = QuestionType.OTHER;
            }

            if(text == null){
                text = "";
            }
            // TODO change depending on the type
            if (text.contains("sex")){
                questionType = QuestionType.NOT_SAFE_FOR_WORK;
            }

            return new Question(id, authorId, text, questionType);
        }
    }
}
