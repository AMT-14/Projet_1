package ch.heigvd.amt.application.identitymng.authenticate;

import ch.heigvd.amt.application.BusinessException;
import lombok.Value;

@Value
public class LoginFailedException extends BusinessException {
    public LoginFailedException(String message){super(message);}
}
