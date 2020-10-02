package ch.heigvd.amt.application;

public class BusinessException {
    public BusinessException(String message) throws Exception {

        throw new java.lang.Exception(message);

    }
}
