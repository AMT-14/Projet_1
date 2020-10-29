package ch.heigvd.amt.domain.vote;

import ch.heigvd.amt.domain.IEntity;
import ch.heigvd.amt.domain.Id;
import ch.heigvd.amt.domain.user.UserId;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Vote implements IEntity<Vote, VoteId> {
    private VoteId id;
    private Id objectVoted;
    private UserId voter;
    private VoteValue value;

    public enum VoteValue{
        DOWN,
        UP
    }

    public static class VoteBuilder{
        public Vote build(){

            if(id == null){
                id = new VoteId();
            }
            if(objectVoted == null){
                throw new IllegalArgumentException("Can't vote on null object");
            }
            if(voter == null){
                throw new IllegalArgumentException("Vote has to come from a user not null");
            }
            if(value == null){
                throw new IllegalArgumentException("Need to specify if it is upvote or downvote");
            }
            return new Vote(id, objectVoted, voter, value);
        }
    }

    @Override
    public Vote deepClone() {
        return this.toBuilder()
                .id(new VoteId(id.asString()))
                .build();
    }


}
