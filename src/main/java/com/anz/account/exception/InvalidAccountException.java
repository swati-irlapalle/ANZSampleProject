package com.anz.account.exception;

public class InvalidAccountException extends RuntimeException {

    public InvalidAccountException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
