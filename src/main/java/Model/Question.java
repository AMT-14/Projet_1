package Model;

public class Question {
    private User author;
    private String question;

    public Question(User author, String question) {
        this.author = author;
        this.question = question;
    }


    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
