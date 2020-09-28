package ch.heigvd.amt.model;

import java.util.Date;

public class Answer extends Post {
    private Post post_to_answer;

    public Answer(User author, String body, Date date, Post post) {
        super(author, body, date);
        this.post_to_answer = post;
        post.addToAnswers(this);
    }
}
