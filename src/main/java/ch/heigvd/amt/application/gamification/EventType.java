package ch.heigvd.amt.application.gamification;

public enum EventType {


    USER_PROGRESS("progress"),
    NEW_QUESTION("question"),
    NEW_VOTE("vote");

    EventType(String event_name){
        this.name = event_name;
    }
    public String name;
}
