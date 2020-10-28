package ch.heigvd.amt.infrastructure.persistence.jdbc;

import ch.heigvd.amt.domain.user.IUserRepository;
import ch.heigvd.amt.domain.user.User;
import ch.heigvd.amt.domain.user.UserId;

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
public class JdbcUserRepository implements IUserRepository {

    @Resource (lookup ="jdbc/DBProjet")
    DataSource dataSource;

    public JdbcUserRepository() {
    }

    public JdbcUserRepository(DataSource dataSource) { this.dataSource = dataSource; }

    @Override
    public void save(User entity) {

        try {

            Connection conn = dataSource.getConnection();
            String query = "INSERT INTO user (user_id, username, email, first_name, last_name, password)"
                    + " VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, entity.getId().asString());
            ps.setString(2, entity.getUsername());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getFirstName());
            ps.setString(5, entity.getLastName());
            ps.setString(6, entity.getEncryptedPassword());

            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
    }

    @Override
    public void remove(UserId id) {

        try {
            Connection conn = dataSource.getConnection();

            String query = "DELETE FROM user WHERE user_id LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id.asString());
            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
    }

    public Optional<User> findById(UserId id) {
        Optional<User> result = Optional.empty();

        try {
            Connection conn = dataSource.getConnection();
            String query = "SELECT * FROM user WHERE user_id LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id.asString());
            ResultSet set = ps.executeQuery();

            if(set.next()) {
                User resultUser = User.builder().
                        id(new UserId(set.getString("user_id")))
                        .username(set.getString("username"))
                        .email(set.getString("email"))
                        .firstName(set.getString("first_name"))
                        .lastName(set.getString("last_name"))
                        .encryptedPassword(set.getString("password")).build();

                result = Optional.of(resultUser);
            }
            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return result;
    }

    @Override
    public Collection<User> findAll() {
        List<User> result = new LinkedList<>();
        try {
            Connection conn = dataSource.getConnection();

            String query = "SELECT * FROM user";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet set = ps.executeQuery();

            while (set.next()) {
                User nextUser = User.builder().
                        id(new UserId(set.getString("user_id")))
                        .username(set.getString("username"))
                        .email(set.getString("email"))
                        .firstName(set.getString("first_name"))
                        .lastName(set.getString("last_name"))
                        .encryptedPassword(set.getString("password")).build();

                result.add(nextUser);
            }

            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return result;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<User> result = Optional.empty();

        try {
            Connection conn = dataSource.getConnection();

            String query = "SELECT * FROM user WHERE username LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ResultSet set = ps.executeQuery();

            if(set.next()) {
                User resultUser = User.builder().
                        id(new UserId(set.getString("user_id")))
                        .username(set.getString("username"))
                        .email(set.getString("email"))
                        .firstName(set.getString("first_name"))
                        .lastName(set.getString("last_name"))
                        .encryptedPassword(set.getString("password")).build();

                result = Optional.of(resultUser);
            }
            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return result;
    }

}
