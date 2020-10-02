package ch.heigvd.amt.application;

public class BusinessException {
    public BusinessException(String message) {

        throw new java.lang.RuntimeException(message);

    }
}
