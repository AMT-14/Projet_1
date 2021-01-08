package ch.heigvd.amt.application.gamification;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PostEventCommand {
    private String name;
    private String inGamifiedAppUserId;
    private String properties;
    // TODO Timestamp here ?
}
