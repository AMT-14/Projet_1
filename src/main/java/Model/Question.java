package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class Question {
    private User author;
    private String body;
/*
    public Question(User author, String body) {
        this.author = author;
        this.body = body;
    }
    */
}
