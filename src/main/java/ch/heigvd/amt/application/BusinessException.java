package ch.heigvd.amt.application;

public class BusinessException extends Exception {
    
    public BusinessException(String message) throws Exception {

        throw new Exception(message);
    }
}
