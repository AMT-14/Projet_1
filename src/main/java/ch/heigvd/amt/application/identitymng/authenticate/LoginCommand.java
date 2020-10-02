package ch.heigvd.amt.application.identitymng.authenticate;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginCommand {
    private String username;
    private String clearTextPassword;
}
