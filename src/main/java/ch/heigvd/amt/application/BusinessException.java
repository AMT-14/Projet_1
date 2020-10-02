package ch.heigvd.amt.application;

public class BusinessException extends Throwable {
    
    public BusinessException(String message) throws Throwable {

        throw new java.lang.Throwable(message);
    }
}
