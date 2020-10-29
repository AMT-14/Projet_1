package ch.heigvd.amt.application.identitymng;

import ch.heigvd.amt.application.identitymng.authenticate.CurrentUserDTO;
import ch.heigvd.amt.application.identitymng.authenticate.LoginCommand;
import ch.heigvd.amt.application.identitymng.authenticate.LoginFailedException;
import ch.heigvd.amt.application.identitymng.login.RegisterCommand;
import ch.heigvd.amt.application.identitymng.login.RegistrationFailedException;
import ch.heigvd.amt.domain.user.IUserRepository;
import ch.heigvd.amt.domain.user.User;

import java.util.Optional;

public class IdentityManagementFacade {

    private IUserRepository userRepository;

    public IdentityManagementFacade(IUserRepository userRepository){this.userRepository = userRepository;}

    public void register(RegisterCommand command) throws Exception {
        Optional<User> existingUserWithSameUsername = userRepository.findByUsername(command.getUsername());

        if(existingUserWithSameUsername.isPresent()){
            throw new RegistrationFailedException("Username is already taken");
        }
        try{
            User newUser = User.builder()
                    .username(command.getUsername())
                    .firstName(command.getFirstName())
                    .lastName(command.getLastName())
                    .email(command.getEmail())
                    .clearTextPassword((command.getClearTextPassword()))
                    .build();

            userRepository.save(newUser);
        } catch (Exception ex){
            throw new RegistrationFailedException(ex.getMessage());
        }
    }


    public CurrentUserDTO authenticate(LoginCommand command) throws Exception {

        User user = userRepository.findByUsername(command.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        boolean success = user.authenticate(command.getClearTextPassword());
        if(!success){
            throw new LoginFailedException("Authentication failed, credential miss-match");
        }

        CurrentUserDTO currentUser = CurrentUserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();

        return currentUser;
    }

}
