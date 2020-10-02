package ch.heigvd.amt.domain.question;


import ch.heigvd.amt.domain.user.User;

import java.util.Date;

public class Question extends Post{

    public Question(User author, String body, Date date) {
        super(author, body, date);
    }
}
