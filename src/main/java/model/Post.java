package model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public abstract class Post {
    private User author;
    private String body;
    private Date date;
    private List<Post> answers;

    public Post(User author, String body, Date date) {
        this.author = author;
        this.body = body;
        this.date = date;
        this.answers = null;
    }

    public void addToAnswers(Post post) {
        answers.add(post);
    }

    
}
