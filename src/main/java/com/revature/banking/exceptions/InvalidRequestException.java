package com.revature.banking.exceptions;

public class InvalidRequestException extends RuntimeException{

    public InvalidRequestException(String message){
        super(message);
    }

}
