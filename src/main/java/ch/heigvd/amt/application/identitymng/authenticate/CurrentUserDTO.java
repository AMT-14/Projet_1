package ch.heigvd.amt.application.identitymng.authenticate;

import ch.heigvd.amt.domain.user.UserId;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CurrentUserDTO {
    private UserId id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
