package ch.heigvd.amt.domain.answer;

import ch.heigvd.amt.domain.IEntity;
import ch.heigvd.amt.domain.question.QuestionId;
import ch.heigvd.amt.domain.user.UserId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

@Getter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Answer implements IEntity<Answer, AnswerId> {

    private AnswerId id;
    private UserId authorId;
    private QuestionId questionId;
    private String text;
    private Date date;


    @Override
    public AnswerId getId() {
        return id;
    }

    @Override
    public Answer deepClone() {
        return this.toBuilder().id(new AnswerId(id.asString())).build();
    }

    public static class AnswerBuilder{
        public Answer build(){
            if(id == null){
                id = new AnswerId();
            }
            if(authorId == null){
                throw new IllegalArgumentException("Author must not be null");
            }
            if(questionId == null){
                throw new IllegalArgumentException("QuestionID must not be null");
            }
            if(text == null){
                throw new IllegalArgumentException("Answer text must not be null");
            }
            if(date == null){
                date = new Date(System.currentTimeMillis());
            }
            return new Answer(id,authorId,questionId,text,date);
        }
    }
}
