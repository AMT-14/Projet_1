package ch.heigvd.amt.domain.question;


import ch.heigvd.amt.domain.IEntity;
import ch.heigvd.amt.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class Question implements IEntity {

    @Setter(AccessLevel.NONE)
    private QuestionId id = new QuestionId();

    private String author;

    private String text;

    @Setter(AccessLevel.NONE)
    private QuestionType questionType;

    public void categorizeAs(QuestionType questionType){this.questionType = questionType;}

    public static  class  QuestionBuilder{
        public Question build(){
            if(id == null){
                id = new QuestionId();
            }

            if(author == null){
                author = "deleted User";
            }

            if(questionType == null){
                questionType = QuestionType.OTHER;
            }

            if(text == null){
                text = "";
            }

            if (text.contains("sex")){
                questionType = QuestionType.NOT_SAFE_FOR_WORK;
            }

            return new Question(id, author, text, questionType);
        }
    }
}