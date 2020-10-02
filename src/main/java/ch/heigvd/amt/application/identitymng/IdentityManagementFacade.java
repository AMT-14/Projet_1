package ch.heigvd.amt.application.identitymng;

import ch.heigvd.amt.application.identitymng.authenticate.CurrentUserDTO;
import ch.heigvd.amt.application.identitymng.authenticate.LoginCommand;
import ch.heigvd.amt.application.identitymng.authenticate.LoginFailedException;
import ch.heigvd.amt.application.identitymng.login.RegisterCommand;
import ch.heigvd.amt.application.identitymng.login.RegistrationFailedException;
import ch.heigvd.amt.domain.user.IUserRepository;
import ch.heigvd.amt.domain.user.User;

public class IdentityManagementFacade {

    private IUserRepository userRepository;

    public IdentityManagementFacade(IUserRepository userRepository){this.userRepository = userRepository;}

    public void register(RegisterCommand command) throws RegistrationFailedException {
        User existingUserWithSameUsername = userRepository.findByUsername(command.getUsername()).orElse(null);

        if(existingUserWithSameUsername != null){
            throw new RegistrationFailedException("Usernamed already taken");
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

    public CurrentUserDTO authenticate(LoginCommand command) throws LoginFailedException{

        User user = userRepository.findByUsername(command.getUsername())
                .orElseThrow(() -> new LoginFailedException("User not found"));
        boolean success = user.authenticate(command.getClearTextPassword());
        if(!success){
            throw new LoginFailedException("Authentication failed, credential missmatch");
        }

        CurrentUserDTO currentUser = CurrentUserDTO.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();

        return currentUser;
    }
}
