package ch.heigvd.amt.application.vote;

import ch.heig.gamification.ApiException;
import ch.heigvd.amt.application.gamification.EventType;
import ch.heigvd.amt.application.gamification.GamificationFacade;
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
    private GamificationFacade gamificationFacade;

    public VoteFacade(IVoteRepository repository, GamificationFacade gamificationFacade) {
        this.repository = repository;
        this.gamificationFacade = gamificationFacade;
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

    public void vote(VoteCommand command) {
        Vote vote = Vote.builder()
                .voter(command.getVoter())
                .objectVoted(command.getObjectVoted())
                .value(command.getValue())
                .build();
        
        if (vote.getValue() == Vote.VoteValue.UP) {
            gamificationFacade.PostEvent(command.getVoter().toString(), EventType.EVENT_UP_VOTE, null);
        } else {
            gamificationFacade.PostEvent(command.getVoter().toString(), EventType.EVENT_DOWN_VOTE, null);
        }
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
