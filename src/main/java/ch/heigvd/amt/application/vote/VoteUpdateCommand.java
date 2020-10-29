package ch.heigvd.amt.application.vote;

import ch.heigvd.amt.domain.Id;
import ch.heigvd.amt.domain.user.UserId;
import ch.heigvd.amt.domain.vote.Vote;
import ch.heigvd.amt.domain.vote.VoteId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@EqualsAndHashCode
@Getter
public class VoteUpdateCommand {
    @Builder.Default
    private VoteId id =null;
    @Builder.Default
    private UserId voter = null;
    @Builder.Default
    private Id objectVoted = null;
    @Builder.Default
    private Vote.VoteValue value = Vote.VoteValue.UP;
}
