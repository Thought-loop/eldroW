package com.thoughtloop.eldroW.exception;

public class InvalidWordException extends Exception{

    private static final long serialVersionUID = 1L;

    public InvalidWordException(){ super("**The word is not 5 letters OR contains a non-letter character**");}

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
