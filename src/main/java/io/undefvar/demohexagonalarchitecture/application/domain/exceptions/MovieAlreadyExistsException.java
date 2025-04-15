package io.undefvar.demohexagonalarchitecture.application.domain.exceptions;

public class MovieAlreadyExistsException extends RuntimeException{
    public MovieAlreadyExistsException(String message){
        super(message);
    }
}
