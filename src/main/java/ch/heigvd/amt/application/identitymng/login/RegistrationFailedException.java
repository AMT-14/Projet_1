package ch.heigvd.amt.application.identitymng.login;

import ch.heigvd.amt.application.BusinessException;
import lombok.Value;

@Value
public class RegistrationFailedException extends BusinessException {
    public RegistrationFailedException(String message) throws Exception {super(message);}
}
