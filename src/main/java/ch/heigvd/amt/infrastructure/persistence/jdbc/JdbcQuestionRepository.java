package ch.heigvd.amt.infrastructure.persistence.jdbc;

import ch.heigvd.amt.application.question.QuestionsQuery;
import ch.heigvd.amt.domain.question.IQuestionRepository;
import ch.heigvd.amt.domain.question.Question;
import ch.heigvd.amt.domain.question.QuestionId;
import ch.heigvd.amt.domain.question.QuestionType;
import ch.heigvd.amt.domain.user.User;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@Named("JdbcUserRepository")
public class JdbcQuestionRepository implements IQuestionRepository {

    @Resource(lookup = "DPprojet1")
    DataSource dataSource;

    @Override
    public Collection<Question> find(QuestionsQuery query) {
        return null;
    }

    @Override
    public void save(Question entity) {
        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO question "
                    + "VALUES (" + entity.getId()
                    + "," + entity.getAuthor()
                    + "," + entity.getQuestionType()
                    + "," + entity.getText() + ")");
            ps.executeQuery();

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

            PreparedStatement ps = conn.prepareStatement("DELETE FROM question WHERE question_id = " + id.toString());
            ps.executeQuery();

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

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM question WHERE question_id = " + id.toString());
            ResultSet set = ps.executeQuery();

            set.next();

            Question resultQuestion = Question.builder()
                    .id(new QuestionId(set.getString("question_id")))
                    .author(set.getString("author_id"))
                    .questionType((QuestionType)set.getObject("question_type"))
                    .text(set.getString("text")).build();

            result = Optional.of(resultQuestion);
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

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM question");
            ResultSet set = ps.executeQuery();

            while (set.next()) {
                Question nextQuestion = Question.builder()
                        .id(new QuestionId(set.getString("question_id")))
                        .author(set.getString("author_id"))
                        .questionType((QuestionType)set.getObject("question_type"))
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
