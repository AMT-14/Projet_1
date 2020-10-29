package ch.heigvd.amt.application.vote;

import ch.heigvd.amt.domain.vote.Vote;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@EqualsAndHashCode
@Getter
public class VoteDTO {
    private String id;
    private String voter;
    private Vote.VoteValue value;
}
