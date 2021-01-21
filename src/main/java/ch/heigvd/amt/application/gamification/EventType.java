package ch.heigvd.amt.application.gamification;

public enum EventType {


    EVENT_FIRST_DOWN_VOTE("eventFirstDownVote"),
    EVENT_FIRST_UP_VOTE("eventFirstUpVote"),
    EVENT_FIRST_QUESTION("eventFirstQuestion"),
    USER_PROGRESS("progress"),
    NEW_QUESTION("question"),
    NEW_VOTE("vote");

    EventType(String event_name){
        this.name = event_name;
    }
    public String name;
}
