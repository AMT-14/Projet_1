package main.java.Model;

public class Question {
    private User author;
    private String body;

    public Question(User author, String question) {
        this.author = author;
        this.body = question;
    }


    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getQuestion() {
        return body;
    }

    public void setQuestion(String question) {
        this.body = question;
    }
}
