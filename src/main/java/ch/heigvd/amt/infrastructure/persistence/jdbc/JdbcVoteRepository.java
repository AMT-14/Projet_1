package ch.heigvd.amt.infrastructure.persistence.jdbc;

import ch.heigvd.amt.domain.Id;
import ch.heigvd.amt.domain.TextType;
import ch.heigvd.amt.domain.answer.AnswerId;
import ch.heigvd.amt.domain.question.QuestionId;
import ch.heigvd.amt.domain.user.IUserRepository;
import ch.heigvd.amt.domain.user.User;
import ch.heigvd.amt.domain.user.UserId;
import ch.heigvd.amt.domain.vote.IVoteRepository;
import ch.heigvd.amt.domain.vote.Vote;
import ch.heigvd.amt.domain.vote.VoteId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@Named("JdbcVoteRepository")
public class JdbcVoteRepository implements IVoteRepository {

    @Resource (lookup ="jdbc/DBProjet")
    DataSource dataSource;

    public JdbcVoteRepository() {
    }

    public JdbcVoteRepository(DataSource dataSource) { this.dataSource = dataSource; }

    private Vote resultSetVoted(ResultSet rs, Id objectVoted) throws SQLException{
        return Vote.builder()
                .id(new VoteId(rs.getString("id")))
                .voter(new UserId(rs.getString("idUser")))
                .objectVoted(objectVoted)
                .value(rs.getBoolean("value") ? Vote.VoteValue.UP : Vote.VoteValue.DOWN)
                .build();
    }

    @Override
    public int totalVotes(Id id, TextType textType) {
        int total = 0;


        try {
            Connection conn = dataSource.getConnection();

            String query;
            if(textType.equals(TextType.QUESTION)){
                query = "select * from vote where object_voted like ?";
            } else if(textType.equals(TextType.ANSWER)) {
                query = "select * from vote where object_voted like ?";
            } else {
                query = "error";
            }

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id.asString());
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                if(rs.getBoolean("value")) {
                    total++;
                } else {
                    total--;
                }
            }

            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcVoteRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return total;
    }

    @Override
    public Optional<Vote> getVote(Id id, UserId uid, TextType textType) {
        Optional<Vote> vote = Optional.empty();
        try {
            Connection conn = dataSource.getConnection();

            String query="SELECT * FROM vote WHERE id LIKE ? AND author_id like ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id.asString());
            ps.setString(2, uid.asString());
            ResultSet rs = ps.executeQuery();

            Collection<Vote> votes = new LinkedList<>();
            if(textType.equals(TextType.QUESTION)){
                while(rs.next()){
                    votes.add(resultSetVoted(rs, uid));
                }
            } else if(textType.equals(TextType.ANSWER)) {
                while(rs.next()){
                    votes.add(resultSetVoted(rs, new AnswerId(rs.getString("author_id"))));
                }
            }

            if(votes.size() == 1){
                vote = votes.stream().findFirst();
            } else if(votes.size() > 1){
                throw new SQLDataException("2 things with same ID");
            }

            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcVoteRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return vote;
    }

    @Override
    public Optional<Vote> getVote(VoteId id, UserId voterId) {
        Optional<Vote> vote = Optional.empty();
        System.out.println(id + " in jdbc vote repo");

        try {
            Connection conn = dataSource.getConnection();

            String query="SELECT * FROM vote WHERE id LIKE ? AND voter_id LIKE ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id.asString());
            ps.setString(2, voterId.asString());
            ResultSet rs = ps.executeQuery();

            Collection<Vote> votes = new LinkedList<>();
            while(rs.next()) {
                votes.add(resultSetVoted(rs, new QuestionId((rs.getString("author_id")))));
            }
            /*
            if(textType.equals(TextType.QUESTION)){
                while(rs.next()){
                    votes.add(resultSetVoted(rs, new QuestionId(rs.getString("author_id"))));
                }
            } else if(textType.equals(TextType.ANSWER)) {
                while(rs.next()){
//                    votes.add(resultSetVoted(rs, new AnswerId(rs.getString("author_id"))));
                }
            }*/



            if(votes.size() == 1){
                vote = votes.stream().findFirst();
            } else if(votes.size() > 1){
                throw new SQLDataException("2 things with same ID");
            }

            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcVoteRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return vote;
    }

    @Override
    public void save(Vote vote) {

        try {
            Connection conn = dataSource.getConnection();
            String query = "INSERT INTO vote (vote_id, object_voted, voter_id, value)"
                    + " VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, vote.getId().asString());
            ps.setString(2, vote.getObjectVoted().asString());
            ps.setString(3, vote.getVoter().asString());
            ps.setBoolean(4, vote.getValue() == Vote.VoteValue.UP); // pour booleaniser

            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
    }

    @Override
    public void remove(VoteId voteId) {

        try {
            Connection conn = dataSource.getConnection();

            String query = "DELETE FROM vote WHERE vote_id LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, voteId.asString());
            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
    }

    @Override
    public void update(Vote vote) {

        try {
            Connection conn = dataSource.getConnection();

            String query = "UPDATE FROM vote SET value LIKE ? WHERE vote_id LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, vote.getValue() == Vote.VoteValue.UP);
            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
    }

    @Override
    public Optional<Vote> findById(VoteId voteId) {
        return Optional.empty();
    }

    @Override
    public Collection<Vote> findAll() {
        return null;
    }
}
