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

    @Resource(lookup = "jdbc/DBProjet")
    DataSource dataSource;

    public JdbcUserRepository() {
    }

    public JdbcUserRepository(DataSource dataSource) { this.dataSource = dataSource; }

    @Override
    public void save(User entity) {
        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO user "
                    + "VALUES (" + entity.getId()
                    + "," + entity.getUsername()
                    + "," + entity.getEmail()
                    + "," + entity.getFirstName()
                    + "," + entity.getLastName()
                    + "," + entity.getEncryptedPassword() + ")");
            ps.executeQuery();

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

            PreparedStatement ps = conn.prepareStatement("DELETE FROM user WHERE user_id = " + id.toString());
            ps.executeQuery();

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

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE user_id = " + id.toString());
            ResultSet set = ps.executeQuery();

            set.next();

            User resultUser = User.builder().
                    id(new UserId(set.getString("user_id")))
                    .username(set.getString("username"))
                    .email(set.getString("email"))
                    .firstName(set.getString("first_name"))
                    .lastName(set.getString("last_name"))
                    .encryptedPassword(set.getString("password")).build();

            result = Optional.of(resultUser);
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

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM USER");
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

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE username = " + username);
            ResultSet set = ps.executeQuery();

            set.next();

            User resultUser = User.builder().
                    id(new UserId(set.getString("user_id")))
                    .username(set.getString("username"))
                    .email(set.getString("email"))
                    .firstName(set.getString("first_name"))
                    .lastName(set.getString("last_name"))
                    .encryptedPassword(set.getString("password")).build();

            result = Optional.of(resultUser);
            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return result;
    }

}
