package ch.heigvd.amt.application.identitymng;

import ch.heigvd.amt.application.ServiceRegistry;
import ch.heigvd.amt.application.identitymng.authenticate.CurrentUserDTO;
import ch.heigvd.amt.application.identitymng.authenticate.LoginCommand;
import ch.heigvd.amt.application.identitymng.authenticate.LoginFailedException;
import ch.heigvd.amt.application.identitymng.login.RegisterCommand;
import ch.heigvd.amt.application.identitymng.login.RegistrationFailedException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

public class IdentityManagementFacadeTest {

//    public static IdentityManagementFacade facade;
//    public static ServiceRegistry registry;
//    public static RegisterCommand registerCommand;
//    public static LoginCommand loginCommand;
//    public static String clearTextPassword = "N!ceP@ssw0rdBruh";
//
//    @BeforeAll
//    public static void IdentityManagementFacadeInitialization() throws Exception {
//
//        facade = registry.getIdentityManagementFacade();
//
//        registerCommand = RegisterCommand.builder()
//                .username("Username")
//                .email("username@domain.ch")
//                .firstName("Test")
//                .lastName("USER")
//                .clearTextPassword(clearTextPassword)
//                .build();
//
//        loginCommand = LoginCommand.builder()
//                .username(registerCommand.getUsername())
//                .clearTextPassword(clearTextPassword)
//                .build();
//
////        facade.register(registerCommand);
//        try {
//            facade.authenticate(LoginCommand.builder().username("Username").clearTextPassword(clearTextPassword).build());
//        } catch (LoginFailedException e) {
//            facade.register(registerCommand);
//        }
//    }
//
//    @Test
//    public void registerNotNullTest() throws Exception {
//        assertNotNull(facade.authenticate(LoginCommand.builder().username("Username").clearTextPassword(clearTextPassword).build()));
//    }
//
//    @Test
//    public void registerDontDuplicateTest(){
//        assertThrows(RegistrationFailedException.class, () -> {
//            facade.register(registerCommand);
//            facade.register(registerCommand);
//        });
//    }
//    //TODO test if we can register a User with not all informations
//
//    @Test
//    public void loginValidUserTest() throws Exception {
//        assertNotNull(facade.authenticate(loginCommand));
//    }
//
//    @Test
//    public void loginInvalidUserTest(){
//        assertThrows(LoginFailedException.class, () -> {
//            LoginCommand falseLoginCommand = LoginCommand.builder()
//                    .username("Santa")
//                    .clearTextPassword("Christmas")
//                    .build();
//
//            facade.authenticate(falseLoginCommand);
//        });
//    }
//
//    @Test
//    public void loginInvalidUsersPasswordTest(){
//        assertThrows(LoginFailedException.class, () -> {
//            LoginCommand falseLoginCommand = LoginCommand.builder()
//                    .username(registerCommand.getUsername())
//                    .clearTextPassword("wrongPassword")
//                    .build();
//
//            facade.authenticate(falseLoginCommand);
//        });
//    }
//
//    @Test
//    public void DTOisValidTest() throws Exception {
//        CurrentUserDTO currentUserLoggedIn = facade.authenticate(loginCommand);
//        assertEquals(currentUserLoggedIn.getUsername(), registerCommand.getUsername());
//        assertEquals(currentUserLoggedIn.getFirstName(), registerCommand.getFirstName());
//        assertEquals(currentUserLoggedIn.getLastName(), registerCommand.getLastName());
//        assertEquals(currentUserLoggedIn.getEmail(), registerCommand.getEmail());
//    }
}
