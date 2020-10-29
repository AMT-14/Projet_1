package ch.heigvd.amt.domain.vote;

import ch.heigvd.amt.domain.IRepository;
import ch.heigvd.amt.domain.Id;
import ch.heigvd.amt.domain.TextType;
import ch.heigvd.amt.domain.user.UserId;

import java.util.Optional;

public interface IVoteRepository extends IRepository<Vote, VoteId> {
    int totalVotes(Id id, TextType textType);
    Optional<Vote> getVote(Id id, UserId uid, TextType textType);
    Optional<Vote> getVote(VoteId id, TextType textType);
}
