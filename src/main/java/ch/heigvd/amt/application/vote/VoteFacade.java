package ch.heigvd.amt.application.vote;

import ch.heigvd.amt.domain.Id;
import ch.heigvd.amt.domain.TextType;
import ch.heigvd.amt.domain.question.QuestionId;
import ch.heigvd.amt.domain.user.UserId;
import ch.heigvd.amt.domain.vote.IVoteRepository;
import ch.heigvd.amt.domain.vote.Vote;
import ch.heigvd.amt.domain.vote.VoteId;

import java.util.Optional;

public class VoteFacade {
    private IVoteRepository repository;

    public VoteFacade(IVoteRepository repository){
        this.repository = repository;
    }

    public VoteDTO getVote(VoteId id, UserId voterId){


        Optional<Vote> vote = repository.getVote(id, voterId);

        return vote.map(value -> VoteDTO.builder()
            .id(value.getId().asString())
            .voter(value.getVoter().asString())
            .value(value.getValue()).build())
                .orElse(null);
    }

    public VoteDTO getVote(Id id, UserId uid, TextType textType){
        Optional<Vote> vote = repository.getVote(id, uid, textType);


        return vote.map(value -> VoteDTO.builder()
                .id(value.getId().asString())
                .voter(value.getVoter().asString())
                .value(value.getValue()).build())
                .orElse(null);
    }

    public void vote(VoteCommand command){
        Vote vote = Vote.builder()
                .voter(command.getVoter())
                .objectVoted(command.getObjectVoted())
                .value(command.getValue())
                .build();

        repository.save(vote);
    }

    public void removeVote(VoteId id){
        repository.remove(id);
    }

    public void modifyVote(VoteUpdateCommand command){
        repository.update(Vote.builder()
                .id(command.getId())
                .voter(command.getVoter())
                .objectVoted(command.getObjectVoted())
                .value(command.getValue())
                .build()
        );
    }

    public int getTotalVotes(Id id, TextType textType){
        return repository.totalVotes(id, textType);
    }

    public int getTotalVotes(QuestionId id){
        return getTotalVotes(id, TextType.QUESTION);
    }
}
