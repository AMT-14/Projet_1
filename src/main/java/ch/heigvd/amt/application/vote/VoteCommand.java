package ch.heigvd.amt.application.vote;

import ch.heigvd.amt.domain.Id;
import ch.heigvd.amt.domain.user.UserId;
import ch.heigvd.amt.domain.vote.Vote;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class VoteCommand {

    @Builder.Default
    private Id objectVoted = null;
    @Builder.Default
    private UserId voter = null;
    @Builder.Default
    private Vote.VoteValue value = Vote.VoteValue.UP;
}
