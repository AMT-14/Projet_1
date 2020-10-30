package ch.heigvd.amt.infrastructure.persistence.jdbc;

import ch.heigvd.amt.domain.answer.Answer;
import ch.heigvd.amt.domain.answer.AnswerId;
import ch.heigvd.amt.domain.answer.IAnswerRepository;
import ch.heigvd.amt.domain.question.QuestionId;
import ch.heigvd.amt.domain.user.UserId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@Named("JdbcAnswerRepository")
public class JdbcAnswerRepository implements IAnswerRepository {

    @Resource(lookup = "jdbc/DBProjet")
    DataSource dataSource;

    @Override
    public List<Answer> findAnswersToThisQuestion(QuestionId id) {
        return null;
    }

    @Override
    public void save(Answer answer) {

        try {
            Connection conn = dataSource.getConnection();
            String query = "INSERT INTO answer (answer_id, author_id, question_id, text, date)"
                    + " VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, answer.getId().asString());
            ps.setString(2, answer.getAuthorId().asString());
            ps.setString(3, answer.getQuestionId().asString());
            ps.setString(4, answer.getText()); // pour booleaniser
            ps.setString(5, answer.getDate().toString());

            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcAnswerRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
    }

    @Override
    public void remove(AnswerId answerId) {

        try {
            Connection conn = dataSource.getConnection();

            String query = "DELETE FROM answer WHERE answer_id LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, answerId.asString());
            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcAnswerRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
    }

    @Override
    public void update(Answer answer) {

        try {
            Connection conn = dataSource.getConnection();

            String query = "UPDATE answer SET text = ?, date = ? WHERE answer_id LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, answer.getText());
            ps.setString(2, answer.getDate());
            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcAnswerRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
    }

    @Override
    public Optional<Answer> findById(AnswerId answerId) {
        Optional<Answer> result = Optional.empty();

        try {
            Connection conn = dataSource.getConnection();
            String query = "SELECT * FROM answer WHERE answer_id LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, answerId.asString());
            ResultSet set = ps.executeQuery();

            if(set.next()) {
                Answer resultAnswer = Answer.builder().
                        id(new AnswerId(set.getString("answer_id")))
                        .authorId(new UserId(set.getString("author_id")))
                        .questionId(new QuestionId(set.getString("question_id")))
                        .text(set.getString("text"))
                        .date(set.getString("date"))
                        .build();

                result = Optional.of(resultAnswer);
            }
            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcAnswerRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return result;
    }

    @Override
        public Collection<Answer> findAll() {
        // TODO
        return null;
     }
}
