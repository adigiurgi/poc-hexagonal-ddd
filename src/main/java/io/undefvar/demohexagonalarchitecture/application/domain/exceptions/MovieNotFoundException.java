package io.undefvar.demohexagonalarchitecture.application.domain.exceptions;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(String message){
        super(message);
    }
}
