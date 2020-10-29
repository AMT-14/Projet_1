package ch.heigvd.amt.domain.question;

import ch.heigvd.amt.domain.user.User;
import ch.heigvd.amt.domain.user.UserId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuestionTest {

    public Question question;
    @BeforeEach

    public void createUser(){
        User user = User.builder().id(new UserId("test")).build();
        question = Question.builder()
                .id(new QuestionId())
                .authorId(user.getId())
                .text("How much wood would a woodchuck chuck if a woodchuck could chuck wood?")
                .questionType(QuestionType.EXISTENTIAL)
                .build();
    }

    @Test
    public void testAuthor(){
        assertEquals("test", question.getId());
    }

    @Test
    public void testText(){
        assertEquals("How much wood would a woodchuck chuck if a woodchuck could chuck wood?", question.getText());
    }
    @Test
    public void testQuestionType(){
        assertEquals(QuestionType.EXISTENTIAL, question.getQuestionType());
    }

}
