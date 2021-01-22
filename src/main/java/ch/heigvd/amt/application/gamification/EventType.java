package ch.heigvd.amt.application.gamification;

public enum EventType {


    EVENT_DOWN_VOTE("eventDownVote"),
    EVENT_UP_VOTE("eventUpVote"),
    EVENT_QUESTION("eventQuestion"),
    USER_PROGRESS("progress"),
    NEW_QUESTION("question"),
    NEW_VOTE("vote");

    EventType(String event_name){
        this.name = event_name;
    }
    public String name;
}
