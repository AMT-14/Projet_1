package ch.heigvd.amt.model;


import java.util.Date;

public class Question extends Post{

    public Question(User author, String body, Date date) {
        super(author, body, date);
    }
}
