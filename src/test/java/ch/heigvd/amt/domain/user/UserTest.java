package ch.heigvd.amt.domain.user;

//import ch.heigvd.amt.domain.user;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {

    public User user;
    @BeforeEach
    public void createUser(){
        user = User.builder()
                .username("TestUSER")
                .firstName("Test")
                .lastName("USER")
                .email("test@USER.ch")
                .clearTextPassword("TheGame")
                .build();
    }

    @Test
    public void testUsername(){
        assertEquals("TestUSER", user.getUsername());
    }

    @Test
    public void testFirstName(){
        assertEquals("Test", user.getFirstName());
    }

    @Test
    public void testLastName(){
        assertEquals("USER", user.getLastName());
    }

    @Test
    public void testEmail(){
        assertEquals("test@USER.ch", user.getEmail());
    }
}
