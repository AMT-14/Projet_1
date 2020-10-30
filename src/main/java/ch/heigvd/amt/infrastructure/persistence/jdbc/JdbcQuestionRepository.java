package ch.heigvd.amt.infrastructure.persistence.jdbc;

import ch.heigvd.amt.domain.question.IQuestionRepository;
import ch.heigvd.amt.domain.question.Question;
import ch.heigvd.amt.domain.question.QuestionId;
import ch.heigvd.amt.domain.question.QuestionType;
import ch.heigvd.amt.domain.user.UserId;

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
import java.util.stream.Collectors;

@ApplicationScoped
@Named("JdbcQuestionRepository")
public class JdbcQuestionRepository implements IQuestionRepository {

    @Resource(lookup = "jdbc/DBProjet")
    DataSource dataSource;

    @Override
    public Collection<Question> find(QuestionId query) {
        if(query != null){
            return findAll().stream()
                    .filter(question -> question.getQuestionType() != QuestionType.NOT_SAFE_FOR_WORK)
                    .collect(Collectors.toList());
        }
        return findAll();
    }

    @Override
    public void save(Question entity) {
        try {
            Connection conn = dataSource.getConnection();
            //String userQuery = "SELECT * FROM user WHERE user_id LIKE ?";
            String query = " INSERT INTO question (question_id, author_id, question_type, text)"
                    + " values (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, entity.getId().asString());
            //ps.setRef(2, authorRef);
            ps.setString(2, entity.getAuthorId().asString());
            ps.setString(3, entity.getQuestionType().name());
            ps.setString(4, entity.getText());

            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
    }

    @Override
    public void remove(QuestionId id) {
        try {
            Connection conn = dataSource.getConnection();

            String query = "DELETE FROM question WHERE question_id LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id.asString());
            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
    }

    @Override
    public void update(Question question) {
        try {
            Connection conn = dataSource.getConnection();

            String query = "UPDATE question SET question_type = ?, text = ? WHERE question_id LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, question.getQuestionType().name());
            ps.setString(2, question.getText());

            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
    }


    @Override
    public Optional<Question> findById(QuestionId id) {
        Optional<Question> result = Optional.empty();

        try {
            Connection conn = dataSource.getConnection();

            String query = "SELECT * FROM question WHERE question_id LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id.asString());
            ResultSet set = ps.executeQuery();

            if(set.next()) {

                Question resultQuestion = Question.builder()
                        .id(new QuestionId(set.getString("question_id")))
                        .authorId(new UserId(set.getString("author_id")))
                        .questionType(QuestionType.valueOf(set.getObject("question_type").toString()))
                        .text(set.getString("text")).build();

                result = Optional.of(resultQuestion);
            }
            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return result;
    }

    @Override
    public Collection<Question> findAll() {
        List<Question> result = new LinkedList<>();
        try {
            Connection conn = dataSource.getConnection();


            String query = "SELECT * FROM question";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet set = ps.executeQuery();

            while (set.next()) {
                Question nextQuestion = Question.builder()
                        .id(new QuestionId(set.getString("question_id")))
                        .authorId(new UserId(set.getString("author_id")))
                        .questionType(QuestionType.valueOf(set.getObject("question_type").toString()))
                        .text(set.getString("text")).build();

                result.add(nextQuestion);
            }

            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return result;
    }
}
